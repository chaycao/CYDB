package com.chaycao.cydb.data;

/**
 * Created by chaycao on 2017/10/18.
 * chaycao@163.com
 *
 * DbData为CYDB中所有基础类型的接口
 * Data接口把输入的类型确定为T，这里的T就是由标准输入得到的基本类型
 * 目前实现该接口的类型有：
 * 1. DbString 字符串类型
 */
public interface DbData<T> {
    /**
     * 赋值
     * @param v
     */
    void set(T v);

    /**
     * 用于显示打印
     * @return
     */
    String toString();

}