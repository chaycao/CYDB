package com.chaycao.cydb.server;

import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.string.DbString;

/**
 * Created by chaycao on 2017/10/19.
 * chaycao@163.com
 *
 * 服务接口
 */
public interface Server {

    public void put(DbString k, DbData v);

    public DbData get(DbString k);
}
