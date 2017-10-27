package com.chaycao.cydb.processor.impl;

import com.chaycao.cydb.dataSource.DataSource;
import com.chaycao.cydb.dataSource.impl.SimpleDataSource;
import com.chaycao.cydb.operation.impl.SimpleOperation;
import com.chaycao.cydb.processor.AbstractProcessor;
import com.chaycao.cydb.server.Server;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 *
 * 一个实例负责处理一个客户端
 */
public class SimpleRequestProcessor extends AbstractProcessor implements Runnable {
    private static List pool = new LinkedList<Socket>();  // 线程池
    private DataSource source;
    private int numDataSources; //数据源编号上限

    public SimpleRequestProcessor(int numDataSources) {
        this.numDataSources = numDataSources;
        source = new SimpleDataSource(0); //初始默认0号数据库
    }

    /**
     * 处理新的请求，将请求加到pool的末尾
     * 并通知所有的线程，新请求的到来
     * @param request
     */
    public static void processRequest(Socket request) {
        synchronized (pool) {
            pool.add(pool.size(), request);
            pool.notify();
        }
    }

    /**
     * 若连接池中不为空，则取出一个请求做处理
     * 否则一直等待
     */
    public void run() {
        while (true) {
            Socket conn;
            synchronized (pool) {
                while (pool.isEmpty()) {
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                conn = (Socket) pool.remove(0); //取出第一个请求处理
            }
            // 取得连接后
            try {
                OutputStream out = conn.getOutputStream();
                // 循环处理client的请求
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    String get = in.readLine();
                    if (get != null && get.length() > 0) {
                        // 记录日志
                        System.out.println(get + "  " + Thread.currentThread().getId());
                        SimpleOperation operation = new SimpleOperation(get, source, this);
                        String result = operation.execute() + "\n";
                        out.write(result.getBytes());
                        out.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置数据源
     */
    public void setDataSource(DataSource source) {
        this.source = source;
    }

    public int getNumDataSources() {
        return this.numDataSources;
    }
}
