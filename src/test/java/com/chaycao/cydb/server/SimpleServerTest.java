package com.chaycao.cydb.server;

import com.chaycao.cydb.data.string.impl.SimpleDynamicString;
import com.chaycao.cydb.server.impl.SimpleServer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 */
public class SimpleServerTest {

    @Test
    public void putAndGet() {
        SimpleServer s = new SimpleServer();

        SimpleDynamicString a = new SimpleDynamicString("a");
        SimpleDynamicString b = new SimpleDynamicString("b");
        SimpleDynamicString c = new SimpleDynamicString("c");
        SimpleDynamicString d = new SimpleDynamicString("d");
        SimpleDynamicString e = new SimpleDynamicString("e");
        s.put(a, new SimpleDynamicString("1"));
        s.put(b, new SimpleDynamicString("2"));
        s.put(c, new SimpleDynamicString("3"));
        s.put(d, new SimpleDynamicString("4"));
        s.put(e, new SimpleDynamicString("5"));

        Assert.assertEquals("1", s.get(a).toString());
        Assert.assertEquals("2", s.get(b).toString());
        Assert.assertEquals("3", s.get(c).toString());
        Assert.assertEquals("4", s.get(d).toString());
        Assert.assertEquals("5", s.get(e).toString());
    }
}
