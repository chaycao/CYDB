package com.chaycao.cydb.client.impl;

import com.chaycao.cydb.client.AbstractClient;
import com.chaycao.cydb.util.CommandUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 *
 * 一个SimpleClient连接一个server
 */
public class SimpleClient extends AbstractClient implements Runnable {
    private Socket socket;
    private String host;
    private int port;
    private int indexDataSource = 0;
    private volatile boolean exit = false;

    public SimpleClient(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public void run() {
        try {
            socket = new Socket(host, port);
            OutputStream out = socket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 接收消息线程
            ReadMessage readMessage = new ReadMessage(in, this);
            readMessage.start();
            // 发送消息线程
            SendMessage sendMessage = new SendMessage(out, this);
            sendMessage.start();


            while (!exit) {
            }
            readMessage.interrupt();
            sendMessage.interrupt();
            System.out.println("goodbye");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class SendMessage extends Thread {
        private OutputStream out;
        private SimpleClient client;

        public SendMessage(OutputStream out, SimpleClient client) {
            this.out = out;
            this.client = client;
        }
        public void run() {
            CommandUtil util = new CommandUtil();
            // 循环等待用户输入，然后发送server
            while (!client.exit) {
                try {
                    synchronized (client) {
                        // 输出提示信息
                        StringBuilder hint = new StringBuilder(socket.getInetAddress().getHostAddress() + ":" + client.port);
                        if (client.indexDataSource != 0)
                            hint.append("[" + client.indexDataSource + "]");
                        hint.append("> ");
                        System.out.print(hint);

                        // 读入命令
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        String read = br.readLine();
                        Pattern p = Pattern.compile("\\s+");
                        Matcher m = p.matcher(read);
                        read = m.replaceAll(" ") + "\n";
                        // 检查命令
                        String result = util.vaildCommand(read);
                        if (result.equals("OK") == true) { // 命令合法
                            // 发送数据
                            System.out.println(result);
                            out.write(read.getBytes());
                            out.flush();
                            client.wait();
                        } else if (result.equals("EXIT") == true) {
                            socket.close();
                            client.exit = true;
                        } else {
                            System.out.println(result);
                            continue;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ReadMessage extends Thread {
        private BufferedReader reader;
        private SimpleClient client;

        public ReadMessage(BufferedReader reader, SimpleClient client) {
            this.reader = reader;
            this.client = client;
        }
        public void run() {
            String message = "";
            while (!client.exit) {
                try {
                    message = reader.readLine();
                    // 是否是广播消息
                    if (message.startsWith("BOARD")) {

                    } else { // 若不是广播消息，唤醒sendMessage
                        synchronized (client) {
                            // 特殊情况处理
                            // 1.切换数据库
                            if (message.startsWith("SELECT")) {
                                int index = Integer.valueOf(message.split(" ")[1]);
                                client.indexDataSource = index;
                                client.notify();
                                continue;
                            }
                            // 普通输出
                            System.out.println(message);
                            client.notify();
                        }
                    }
                } catch (IOException e) {

                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleClient client = new SimpleClient("localhost", 8888);
        Thread t = new Thread(client);
        t.start();
    }
}
