package com.chaycao.cydb.server.impl;

import com.chaycao.cydb.dataSource.DataSource;
import com.chaycao.cydb.dataSource.impl.SimpleDataSource;
import com.chaycao.cydb.processor.impl.HttpRequestProcessor;
import com.chaycao.cydb.server.AbstractServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 *
 * HttpServer负责开启HTTP服务，调用HttpRequestProcessor处理请求
 */
public class SimpleServer extends AbstractServer implements Runnable {
    private ServerSocket server;
    private int numThreads = 50; //处理进程数
    private DataSource source;

    /**
     * 指定端口，不加载数据
     * @param port
     */
    public SimpleServer(int port) {
        this(port, null);
    }

    /**
     * 指定端口，加载数据
     * @param port
     * @param dataFile
     */
    public SimpleServer(int port, String dataFile) {
        try {
            this.server = new ServerSocket(port);
            if (dataFile == null)
                this.source = new SimpleDataSource();
            else
                this.source = new SimpleDataSource(dataFile);
        } catch (IOException e) {
            System.out.println("端口被占用！请尝试别的端口");
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Server is initializing...");
        initialize();
        System.out.println("Initialize Success");
        System.out.println("Accepting connection on port "
                + server.getLocalPort());
        while (true){
            try {
                Socket request = server.accept();
                // 调用HttpRequestProcessor处理
                HttpRequestProcessor.processRequest(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化
     */
    public void initialize() {
        // 处理进程初始化
        for (int i = 0; i < numThreads; i++) {
            Thread t = new Thread(new HttpRequestProcessor(source));
            t.start();
        }
    }

    public static void main(String[] args) {
        SimpleServer server = new SimpleServer(8888);
        Thread t = new Thread(server);
        t.start();
    }
}
