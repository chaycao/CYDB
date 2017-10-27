# CYDB

用java实现的key-value数据库

支持命令： 
1. set  
功能：添加key-value  
用法：set key value  
2. get  
功能：根据key，找value  
用法：get key  
3. save  
功能：保存数据库  
用法：save  
4. select  
功能：选择数据库  
用法：select index  

功能描述
1. server线程池支持与client通信
2. 客户端向服务器发送请求，执行put get操作，服务器返回结果
3. 支持类型：String-String
4. 可保存数据、加载数据
5. 支持多数据库，由配置文件指定数据库的数量