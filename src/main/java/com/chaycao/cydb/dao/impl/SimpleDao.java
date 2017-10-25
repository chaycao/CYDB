package com.chaycao.cydb.dao.impl;

import com.chaycao.cydb.dao.AbstractDao;
import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.string.DbString;
import com.chaycao.cydb.dataSource.DataSource;
import com.chaycao.cydb.dataSource.impl.SimpleDataSource;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 */
public class SimpleDao extends AbstractDao {
    private DataSource source;

    public SimpleDao(String dataFile) {
        // source初始化
        if (dataFile == null) {
            source = new SimpleDataSource();
        } else {
            source = new SimpleDataSource(dataFile);
        }
    }

    public void put(DbString k, DbData v) {
        source.put(k, v);
    }

    public DbData get(DbString k) {
        return source.get(k);
    }

    public boolean save(String path) {
        return source.save(path);
    }

    // for test
    public DataSource getSource() {
        return source;
    }
}
