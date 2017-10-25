# CYDB

用java实现的key-value数据库

定个小目标：

1. 支持String-String类型
2. 可增删改查
3. 持久化
4. 加载数据

已完成：
1. DbData,DbMap,DBString的设计
2. Server模块，一个SimpleServer内部用一个SimpleHashMap，可实现put\get操作

问题：
server应该有一个保存所有键值对的map，map<Dbdata,Dbdata>
我以Dbdata的接口保存DbString的值，get返回的value，如何确定该类型
