package com.chaycao.cydb.processor;

import com.chaycao.cydb.dataSource.DataSource;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 */
public interface Processor {
    /**
     * 设置数据源
     */
    public void setDataSource(DataSource source);

    /**
     * 获得数据源个数
     * @return
     */
    public int getNumDataSources();
}
