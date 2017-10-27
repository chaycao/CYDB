package com.chaycao.cydb.operation.impl;

import com.chaycao.cydb.command.impl.Get;
import com.chaycao.cydb.command.impl.Save;
import com.chaycao.cydb.command.impl.Select;
import com.chaycao.cydb.command.impl.Set;
import com.chaycao.cydb.dataSource.DataSource;
import com.chaycao.cydb.processor.Processor;
import com.chaycao.cydb.processor.impl.SimpleRequestProcessor;
import com.chaycao.cydb.server.Server;

/**
 * Created by chaycao on 2017/10/25.
 * chaycao@163.com
 *
 * 一次操作
 * 负责根据指令，操作数据或对数据源做操作
 */
public class SimpleOperation {
    private String command;
    private DataSource source;
    private Processor processor;

    public SimpleOperation(String command, DataSource source, Processor processor) {
        this.command = command;
        this.source = source;
        this.processor = processor;
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
        if (c.equals("select")) {
            Select select = new Select(processor);
            return select.exe(args[1]);
        }
        return "system error";
    }

}
