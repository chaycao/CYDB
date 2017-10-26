package com.chaycao.cydb.dataSource;

import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.string.DbString;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 *
 * DataSource负责存储数据
 */
public interface DataSource {

    public void put(DbString k, DbData v);

    public DbData get(DbString k);

    public boolean load();

    public boolean save();
}
