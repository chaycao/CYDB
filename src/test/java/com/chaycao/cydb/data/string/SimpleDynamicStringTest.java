package com.chaycao.cydb.data.string;

import com.chaycao.cydb.data.string.impl.SimpleDynamicString;
import junit.framework.Assert;
import org.junit.Test;

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
}
