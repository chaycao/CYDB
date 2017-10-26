package com.chaycao.cydb.operation.impl;

import com.chaycao.cydb.command.impl.Get;
import com.chaycao.cydb.command.impl.Save;
import com.chaycao.cydb.command.impl.Set;
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
        String[] args = command.trim().split(" ");
        int len = args.length;
        String c = args[0].toLowerCase();
        if (c.equals("get")) {
            Get get = new Get(source);
            return get.exe(args[1]);
        }
        if (c.equals("set")) {
            Set set = new Set(source);
            return set.exe(args[1], args[2]);
        }
        if (c.equals("save")) {
            Save save = new Save(source);
            return save.exe();
        }
        return "system error";
    }

}
