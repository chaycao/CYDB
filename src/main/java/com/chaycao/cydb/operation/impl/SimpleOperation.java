package com.chaycao.cydb.operation.impl;

import com.chaycao.cydb.data.string.DbString;
import com.chaycao.cydb.data.string.impl.SimpleDynamicString;
import com.chaycao.cydb.dataSource.DataSource;

/**
 * Created by chaycao on 2017/10/25.
 * chaycao@163.com
 *
 * 分析指令
 * get key --- 返回value
 * put key value
 */
public class SimpleOperation {
    private String command;
    private DataSource source;

    public SimpleOperation(String command, DataSource source) {
        this.command = command;
        this.source = source;
    }

    public String execute() {
        String[] args = command.split(" ");
        if (args[0].equals("get")) {
            DbString k = new SimpleDynamicString(args[1]);
            return source.get(k).toString();
        } else if (args[0].equals("put")) {
            DbString k = new SimpleDynamicString(args[1]);
            DbString v = new SimpleDynamicString(args[2]);
            source.put(k, v);
            return "OK";
        }
        return args[0] + " is not a command";
    }
}
