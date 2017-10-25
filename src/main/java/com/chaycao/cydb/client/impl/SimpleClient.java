package com.chaycao.cydb.client.impl;

import com.chaycao.cydb.client.AbstractClient;
import com.chaycao.cydb.server.Server;

/**
 * Created by chaycao on 2017/10/21.
 * chaycao@163.com
 *
 * 一个SimpleClient连接一个server
 */
public class SimpleClient extends AbstractClient {
    private Server server;

    public SimpleClient(Server server) {
        this.server = server;
    }

}
