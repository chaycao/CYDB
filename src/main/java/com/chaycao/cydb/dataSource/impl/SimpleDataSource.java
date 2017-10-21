package com.chaycao.cydb.dataSource.impl;

import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.map.DbMap;
import com.chaycao.cydb.data.map.impl.SimpleHashMap;
import com.chaycao.cydb.data.string.DbString;
import com.chaycao.cydb.dataSource.AbstractDataSource;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 */
public class SimpleDataSource extends AbstractDataSource {
    private DbMap<DbString, DbData> map;

    public SimpleDataSource() {
        map = new SimpleHashMap<DbString, DbData>();
    }

    public void put(DbString k, DbData v) {
        map.put(k, v);
    }

    public DbData get(DbString k) {
        return map.get(k);
    }
}
