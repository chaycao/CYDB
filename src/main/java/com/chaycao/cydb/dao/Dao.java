package com.chaycao.cydb.dao;

import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.string.DbString;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 *
 * Dao负责对数据源进行操作，以及返回结果给Server层
 */
public interface Dao {
    public void put(DbString k, DbData v);

    public DbData get(DbString k);

    public boolean save(String path);
}
