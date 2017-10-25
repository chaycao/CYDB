package com.chaycao.cydb.data.map;

import com.chaycao.cydb.data.DbData;

import java.util.HashMap;

/**
 * Created by chaycao on 2017/10/18.
 * chaycao@163.com
 */
public interface DbMap<K,V> extends DbData {
    void put(K k, V v);
    V get(K k);
    int size();
}
