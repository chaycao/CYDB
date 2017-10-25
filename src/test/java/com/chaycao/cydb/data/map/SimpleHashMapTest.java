package com.chaycao.cydb.data.map;

import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.map.impl.SimpleHashMap;
import com.chaycao.cydb.data.string.DbString;
import com.chaycao.cydb.data.string.impl.SimpleDynamicString;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by chaycao on 2017/10/19.
 * chaycao@163.com
 */
public class SimpleHashMapTest {

    @Test
    public void putString() {
        SimpleHashMap<DbData, DbData> map = new SimpleHashMap<DbData, DbData>();
        DbString key = new SimpleDynamicString("key");
        DbString value = new SimpleDynamicString("value");
        map.put(key, value);
        DbData target = map.get(key);
        System.out.println(target.getType());
        Assert.assertEquals("value", target.toString());
    }
}
