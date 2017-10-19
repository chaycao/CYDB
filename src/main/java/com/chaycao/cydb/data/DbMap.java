package com.chaycao.cydb.data;

/**
 * Created by chaycao on 2017/10/18.
 * chaycao@163.com
 */
public interface DbMap<K,V> {
    void put(K k, V v);
    V get(K k);
}
