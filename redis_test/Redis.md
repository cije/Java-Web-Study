# Redis

1. 概念：redis时一款高性能的NOSQL系列的非关系型数据库

2. 下载安装

3. 命令操作

	1. redis的数据结构：
		- redis存储的是 ：key,value格式的数据，其中key都是字符串  ，value有5中数据结构
			- value的数据结构：
				1. 字符串类型 string
				2. 哈希类型 hash
				3. 列表类型 list
				4. 集合类型  set
				5. 有序集合类型 sortedset
	2. 字符串类型 string
		1. 存储： `set key value`
		2. 获取：`get key`
		3. 删除：`del key`
	3. 哈希类型 hash
		1. 存储： `hset key field value`
		2. 获取：`hget key field`、 `hgetall key`
		3. 删除：`hdel key field`
	4. 列表类型 list：可以添加一个元素到列表的头部或者尾部
		1. 添加：
			1. `lpush key value`：将元素加入列表左边
			2. `rpush key value`：将元素加入列表右边
		2. 获取：
			- `lrange key start end`：范围获取
		3. 删除：
			- `lpop key`：删除列表最左边的元素，并返回
			- rpop key`：删除列表最右边的元素，并返回
	5. 集合类型  set：不允许重复元素
		1. 存储：`sadd key value`
		2. 获取：`smembers key`：获取set集合中所有元素
		3. 删除：`srem key value`：删除set集合中的某个元素
	6. 有序集合类型 sortedset：不允许重复元素，且元素有顺序
		1. 存储：`zadd key score value`
		2. 获取：`zrange key start end`
		3. 删除：`zrem key value`
	7. 通用命令
		1. `keys *`：查询所有的键
		2. `type *`：获取键的类型
		3. `del key`：删除指定的key value

4. 持久化

	1. redis是一个内存数据库，当redis服务器重启，数据就会丢失，我们可以将redis中的数据持久化到硬盘文件中。

	2. redis持久化机制：

		1. `RDB`：默认方式，不需要进行配置，默认使用这种机制

			- 在一定的间隔时间中，检测key的变化情况，，然后持久化数据。

				1. 编辑redis.conf文件

					\# after 900 sec (15 min) if at least 1 key changed

					save 900 1

					\# after 300 sec (5 min) if at least 10 keys changed

					save 300 10

					\# after 60 sec if at least 10000 keys changed

					save 60 10000

				2. 重新启动redis服务器 并指定配置文件名称

		2. `AOF`：日志记录的方式，可以记录每一条命令的操作，可以每一次命令操作后，持久化数据。

			1. 编辑redis.conf文件

				\# appendonly no（关闭aof） yes（开启aof）

				\# appendfsync always ：每一次操作都进行持久化

				\#  appendfsync everysec ：每隔一秒进行一次持久化

				\# appendfsync no ：不进行持久化

5. Java客户端Jedis

	- Jedis：一款Java操作redis数据库的工具。

	- 使用步骤：

		1. 下载Jedis的jar包

		2. 使用

			```java
			//1.获取连接
			Jedis jedis = new Jedis("localhost", 6379);
			//2.操作
			jedis.set("username", "zhangsan");
			//3.关闭连接
			jedis.close();
			```

	- Jedis操作各种redis的数据结构

		1. 字符串类型 string

			set

			get

			```java
			// 可以使用 setex() 方法存储可以指定过期事件的 key value
			// 将 "activecode:"hehe" 存入redis 并且 20 秒后自动删除该键值对
			jedis.setex("activecode", 20, "hehe");
			```

			

		2. 哈希类型 hash

			hset

			hget

		3. 列表类型 list

			lpush  rpush

			lpop    rpop

		4. 集合类型  set

			sadd

			smembers

		5. 有序集合类型 sortedset

			zadd

			zrange

	- jedis连接池：JedisPool

		- 使用：
			1. 创建JedisPool连接池对象
			2. 调用方法 `getResource()` 获取Jedis连接

## 案例

- 案例需求：
	1. 提供index.html页面，页面有一个省份的下拉列表
	2. 当页面加载完后，发送ajax请求，加载所有省份
- 注意：使用redis缓存一些不经常发生变化的数据
	- 数据库的数据一旦发生改变，则需要更新缓存。
		- 数据的表执行 增删改的相关操作，需要将redis缓存数据清空，再次存入
		- 在service对应的增删改方法中，将redis数据删除。

