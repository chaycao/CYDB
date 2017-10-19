package com.chaycao.cydb.data.map.impl;

import com.chaycao.cydb.data.map.AbstractMap;

import java.util.HashMap;

/**
 * Created by chaycao on 2017/10/18.
 * chaycao@163.com
 *
 * 对java.util.hashmap进行包装
 */
public class SimpleHashMap<K,V> extends AbstractMap<K,V>{
    private HashMap<K, V> map;

    public SimpleHashMap() {
        this.map = new HashMap<K, V>();
    }

    public void put(K k, V v) {
        map.put(k, v);
    }

    public final V get(K k) {
        return map.get(k);
    }
}
