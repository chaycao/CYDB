package com.chaycao.cydb.command.impl;

import com.chaycao.cydb.command.AbstractOperation;
import com.chaycao.cydb.data.string.DbString;
import com.chaycao.cydb.data.string.impl.SimpleDynamicString;
import com.chaycao.cydb.dataSource.DataSource;

/**
 * Created by chaycao on 2017/10/26.
 * chaycao@163.com
 */
public class Set extends AbstractOperation {
    private DataSource source;
    public Set(DataSource source) {
        this.source = source;
    }
    public String exe(String k, String v) {
        source.put(new SimpleDynamicString(k), new SimpleDynamicString(v));
        return "OK";
    }
}
