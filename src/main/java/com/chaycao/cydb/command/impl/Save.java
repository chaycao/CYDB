package com.chaycao.cydb.command.impl;

import com.chaycao.cydb.command.AbstractOperation;
import com.chaycao.cydb.dataSource.DataSource;

/**
 * Created by chaycao on 2017/10/26.
 * chaycao@163.com
 * 保存数据库
 */
public class Save extends AbstractOperation {
    private DataSource source;
    public Save(DataSource source) {
        this.source = source;
    }
    public String exe() {
        if (source.save())
            return "OK";
        else
            return "FAIL";
    }
}
