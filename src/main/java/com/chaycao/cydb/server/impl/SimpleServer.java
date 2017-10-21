package com.chaycao.cydb.server.impl;

import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.map.DbMap;
import com.chaycao.cydb.data.map.impl.SimpleHashMap;
import com.chaycao.cydb.data.string.DbString;
import com.chaycao.cydb.server.AbstractServer;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 */
public class SimpleServer extends AbstractServer {
    private DbMap<DbString, DbData> map;

    public SimpleServer() {
        map = new SimpleHashMap<DbString, DbData>();
    }

    public void put(DbString k, DbData v) {
        map.put(k, v);
    }

    public DbData get(DbString k) {
        return map.get(k);
    }
}
