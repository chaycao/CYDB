package com.chaycao.cydb.dao;

import com.chaycao.cydb.dao.impl.SimpleDao;
import com.chaycao.cydb.data.DbData;
import com.chaycao.cydb.data.string.DbString;
import com.chaycao.cydb.data.string.impl.SimpleDynamicString;
import com.chaycao.cydb.dataSource.DataSource;
import com.chaycao.cydb.dataSource.impl.SimpleDataSource;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 */
public class SimpleDaoTest {

    public void putAndGet() {
        // 不加载
        Dao dao = new SimpleDao(null);
        SimpleDynamicString a = new SimpleDynamicString("a");
        SimpleDynamicString b = new SimpleDynamicString("b");
        SimpleDynamicString c = new SimpleDynamicString("c");
        dao.put(a, new SimpleDynamicString("1"));
        dao.put(b, new SimpleDynamicString("2"));
        dao.put(c, new SimpleDynamicString("3"));
        Assert.assertEquals("1", dao.get(a).toString());
        Assert.assertEquals("2", dao.get(b).toString());
        Assert.assertEquals("3", dao.get(c).toString());
    }

    @Test
    public void saveAndLoad() {
        SimpleDao dao = new SimpleDao(null);
        SimpleDynamicString a = new SimpleDynamicString("a");
        SimpleDynamicString b = new SimpleDynamicString("b");
        SimpleDynamicString c = new SimpleDynamicString("c");
        dao.put(a, new SimpleDynamicString("1"));
        dao.put(b, new SimpleDynamicString("2"));
        dao.put(c, new SimpleDynamicString("3"));
        Boolean result_1 = dao.save("E:\\GitHub\\CYDB\\src\\main\\resources\\db.data");
        Assert.assertEquals(true, result_1);
        SimpleDao dao2 = new SimpleDao("E:\\GitHub\\CYDB\\src\\main\\resources\\db.data");
        DataSource dataSource = dao2.getSource();
        Assert.assertEquals("1", dao2.get(a).toString());
        Assert.assertEquals("2", dao2.get(b).toString());
        Assert.assertEquals("3", dao2.get(c).toString());
    }
}