package com.chaycao.cydb.server.impl;

import com.chaycao.cydb.dataSource.DataSource;
import com.chaycao.cydb.dataSource.impl.SimpleDataSource;
import com.chaycao.cydb.processor.impl.SimpleRequestProcessor;
import com.chaycao.cydb.server.AbstractServer;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 *
 * HttpServer负责开启HTTP服务，调用HttpRequestProcessor处理请求
 */
public class SimpleServer extends AbstractServer implements Runnable {
    private ServerSocket server;
    private int numThreads = 50; //处理进程数
    private int numDataSources; //数据源个数


    /**
     * 指定端口
     * @param port
     */
    public SimpleServer(int port) {
        try {
            this.server = new ServerSocket(port);
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
                SimpleRequestProcessor.processRequest(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化
     */
    public void initialize() {
        // 读取dataSourceNum，若文件不存在，则生成
        // 0号数据库作为初始数据库
        ResourceBundle r = ResourceBundle.getBundle("server");
        String num = r.getString("numDataSources");
        String basePath = this.getClass().getClassLoader().getResource(".").getPath();
        if (num == "")
            this.numDataSources = 8; // 默认8个
        else {
            this.numDataSources = Integer.valueOf(num);
        }
        // 处理进程初始化
        for (int i = 0; i < numThreads; i++) {
            Thread t = new Thread(new SimpleRequestProcessor(numDataSources));
            t.start();
        }
    }

    public static void main(String[] args) {
        SimpleServer server = new SimpleServer(8888);
        Thread t = new Thread(server);
        t.start();
    }

}
