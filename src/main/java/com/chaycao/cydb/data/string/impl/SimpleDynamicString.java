package com.chaycao.cydb.data.string.impl;

import com.chaycao.cydb.data.string.AbstractString;

/**
 * Created by chaycao on 2017/10/18.
 * chaycao@163.com
 *
 * 2017.10.18
 * 用StringBuilder保存值
 */
public class SimpleDynamicString extends AbstractString {
    private StringBuilder value;

    public SimpleDynamicString(String v) {
        set(v);
    }

    /**
     * 对value赋值
     * @param v :
     */
    public void set(String v) {
        this.value = new StringBuilder(v);
    }

    /**
     * 取值
     * @return
     */
    public final String toString() {
        return this.value.toString();
    }

}
