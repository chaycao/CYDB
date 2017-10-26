package com.chaycao.cydb.client.impl;

import com.chaycao.cydb.client.AbstractClient;
import com.chaycao.cydb.command.util.CommandUtil;
import com.chaycao.cydb.server.Server;

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
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String read = br.readLine();
                Pattern p = Pattern.compile("\\s+");
                Matcher m = p.matcher(read);
                read = m.replaceAll(" ")+"\n";
                // 检查命令
                CommandUtil util = new CommandUtil();
                String result = util.vaildCommand(read);
                if (result.equals("OK") == false) { // 命令不合法
                    System.out.println(result);
                    continue;
                }
                // 发送数据
                out.write(read.getBytes());
                out.flush();
                String get = in.readLine();
                System.out.println(get);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleClient client = new SimpleClient("localhost", 8888);
        Thread t = new Thread(client);
        t.start();
    }
}
