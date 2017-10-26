package com.chaycao.cydb.command.impl;

import com.chaycao.cydb.data.string.DbString;
import com.chaycao.cydb.data.string.impl.SimpleDynamicString;
import com.chaycao.cydb.dataSource.DataSource;

/**
 * Created by chaycao on 2017/10/26.
 * chaycao@163.com
 */
public class Get {
    private DataSource source;
    public Get(DataSource source) {
        this.source = source;
    }
    public String exe(String k) {
        DbString key = new SimpleDynamicString(k);
        return source.get(key).toString();
    }
}
