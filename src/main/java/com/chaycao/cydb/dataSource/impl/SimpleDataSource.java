package com.chaycao.cydb.dataSource.impl;

import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.map.DbMap;
import com.chaycao.cydb.data.map.impl.SimpleHashMap;
import com.chaycao.cydb.data.string.DbString;
import com.chaycao.cydb.data.string.impl.SimpleDynamicString;
import com.chaycao.cydb.dataSource.AbstractDataSource;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 */
public class SimpleDataSource extends AbstractDataSource {
    private DbMap<DbString, DbData> map;

    public SimpleDataSource() {
        map = new SimpleHashMap<DbString, DbData>();
    }

    public SimpleDataSource(String File) {
        load(File);
    }

    public void put(DbString k, DbData v) {
        map.put(k, v);
    }

    public DbData get(DbString k) {
        return map.get(k);
    }

    public boolean save(String path) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean load(String path) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            this.map = (DbMap<DbString, DbData>)in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
