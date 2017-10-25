package com.chaycao.cydb.processor.impl;

import com.chaycao.cydb.dao.Dao;
import com.chaycao.cydb.dao.impl.SimpleDao;
import com.chaycao.cydb.dataSource.DataSource;
import com.chaycao.cydb.operation.impl.SimpleOperation;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 *
 * 操作Dao层
 * 数据由Dao层加载，加载的位置，应该由Server传过来
 */
public class HttpRequestProcessor implements Runnable {
    private static List pool = new LinkedList<Socket>();  // 线程池
    private DataSource source;

    public HttpRequestProcessor(final DataSource source) {
        this.source = source;
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
            // 取得连接后，对连接做处理
            // 先尝试把client的传过来的信息输出
            try {
                OutputStream out = conn.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while (true) {
                    String get = in.readLine();
                    // 记录日志
                    System.out.println(get + "  " + Thread.currentThread().getId());
                    SimpleOperation operation = new SimpleOperation(get, source);
                    String result = operation.execute()+"\n";
                    out.write(result.getBytes());
                    out.flush();
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
}
