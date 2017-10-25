package com.chaycao.cydb.server.impl;

import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.string.DbString;
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
public class HttpServer extends AbstractServer implements Runnable {
    private ServerSocket server;
    private int numThreads = 50; //处理进程数
    private String dataFile;

    /**
     * 指定端口，不加载数据
     * @param port
     */
    public HttpServer(int port) {
        this(port, null);
    }

    /**
     * 指定端口，加载数据
     * @param port
     * @param dataFile
     */
    public HttpServer(int port, String dataFile) {
        try {
            this.server = new ServerSocket(port);
            this.dataFile = dataFile;
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
                Socket socket = server.accept();
                // need
                // 调用HttpRequestProcessor处理
//                HttpRequestProcessor.
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
            Thread t = new Thread(new HttpRequestProcessor(this.dataFile));
            t.start();
        }
    }
}
