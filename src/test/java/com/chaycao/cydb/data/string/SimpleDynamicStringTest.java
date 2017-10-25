package com.chaycao.cydb.data.string;

import com.chaycao.cydb.data.string.impl.SimpleDynamicString;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by chaycao on 2017/10/18.
 * @author chaycao, chaycao@163.com
 */
public class SimpleDynamicStringTest {

    @Test
    public void constructor() {
        SimpleDynamicString s = new SimpleDynamicString("abc");
        Assert.assertNotNull(s);
    }

    @Test
    public void print() {
        SimpleDynamicString s = new SimpleDynamicString("abc");
        Assert.assertEquals("abc", s.toString());
    }

    @Test
    public void equals() {
        SimpleDynamicString s1 = new SimpleDynamicString("a");
        SimpleDynamicString s2 = new SimpleDynamicString("a");
        if (s1 == s2)
            System.out.println("相等");
        else
            System.out.println("不等");
    }
}
