package com.chaycao.cydb.data;

import java.io.Serializable;

/**
 * Created by chaycao on 2017/10/18.
 * chaycao@163.com
 *
 * DbData为CYDB中所有基础类型的接口
 * 目前实现该接口的类型有：
 * 1. DbString 字符串类型
 */
public interface DbData extends Serializable {

    /**
     * 用于显示打印
     * @return
     */
    String toString();

    /**
     * 返回类型
     * 0:字符串
     * @return
     */
    int getType();
}
