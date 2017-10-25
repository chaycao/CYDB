package com.chaycao.cydb.data.string.impl;

import com.chaycao.cydb.data.string.AbstractString;
import com.chaycao.cydb.data.string.DbString;

/**
 * Created by chaycao on 2017/10/18.
 * chaycao@163.com
 *
 * 2017.10.18
 * 用StringBuilder保存值
 */
public class SimpleDynamicString extends AbstractString {
    private StringBuilder value;
    private int hash;

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

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof DbString) {
            DbString anotherString = (DbString)anObject;
            int n = value.toString().length();
            if (n == anotherString.toString().length()) {
                char v1[] = value.toString().toCharArray();
                char v2[] = anotherString.toString().toCharArray();
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int h = hash;
        int len = value.toString().length();
        if (h == 0 && len > 0) {
            char val[] = value.toString().toCharArray();

            for (int i = 0; i < len; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
}
