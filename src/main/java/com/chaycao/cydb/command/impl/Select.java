package com.chaycao.cydb.command.impl;

import com.chaycao.cydb.command.AbstractOperation;
import com.chaycao.cydb.dataSource.DataSource;
import com.chaycao.cydb.dataSource.impl.SimpleDataSource;
import com.chaycao.cydb.processor.Processor;
import com.chaycao.cydb.server.Server;

/**
 * Created by chaycao on 2017/10/27.
 * chaycao@163.com
 *
 * 选择数据库
 */
public class Select extends AbstractOperation {
    private Processor processor;
    public Select(Processor processor) {
        this.processor = processor;
    }
    public String exe(String str) {
        // 是否超出服务器上限
        int index = Integer.valueOf(str);
        if (index >= processor.getNumDataSources())
            return "(error) ERR invalid DB index";
        processor.setDataSource(new SimpleDataSource(Integer.valueOf(index)));
        return "SELECT " + index;
    }
}
