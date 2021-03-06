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

    public SimpleDataSource(int id) {
        this.id = id;
        load();
    }

    public void put(DbString k, DbData v) {
        map.put(k, v);
    }

    public DbData get(DbString k) {
        return map.get(k);
    }

    public boolean save() {
        String basePath = this.getClass().getClassLoader().getResource(".").getPath();
        String dir = basePath + "db";
        String path = basePath + "db/db" + id + ".data";
        ObjectOutputStream out = null;
        try {
            File d = new File(dir);
            if (!d.exists()) {  //文件夹不存在
                d.mkdir();
            }
            File f = new File(path);
            if (!f.exists()) {  //文件不存在
                f.createNewFile();
            }
            out = new ObjectOutputStream(new FileOutputStream(basePath+"db/db"+id+".data"));
            out.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getId() {
        return this.id;
    }

    public boolean load() {
        String basePath = this.getClass().getClassLoader().getResource(".").getPath();
        String path = basePath + "db/db" + id + ".data";
        try {
            File f = new File(path);
            if (!f.exists() || f.length() == 0) {  //文件内容为空
                this.map = new SimpleHashMap<DbString, DbData>();
            } else {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
                this.map = (DbMap<DbString, DbData>)in.readObject();
            }
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