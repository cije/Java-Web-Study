package ce.jedis.test;

import ce.jedis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis的测试类
 */
public class JedisTest {
    /**
     * 快速入门
     */
    @Test
    public void test1() {
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //2.操作
        jedis.set("username", "zhangsan");
        //3.关闭连接
        jedis.close();
    }

    /**
     * string 数据类型操作
     */
    @Test
    public void test2() {
        //1.获取连接
        // Jedis jedis = new Jedis("localhost", 6379);
        //如果使用空参构造，默认值 “localhost” 6379
        Jedis jedis = new Jedis();
        //2.操作
        jedis.set("username", "张三");
        String username = jedis.get("username");
        System.out.println(username);

        // 可以使用 setex() 方法存储可以指定过期事件的 key value

        // 将 "activecode:"hehe" 存入redis 并且 20 秒后自动删除该键值对
        jedis.setex("activecode", 20, "hehe");

        //3.关闭连接
        jedis.close();
    }

    /**
     * hash 数据结构操作
     */
    @Test
    public void test3() {
        //1.获取连接
        Jedis jedis = new Jedis();
        //2.操作
        //存储hash
        jedis.hset("user", "name", "李四");
        jedis.hset("user", "age", "23");
        jedis.hset("user", "gender", "男");

        //获取hash
        String name = jedis.hget("user", "name");
        System.out.println(name);

        //获取hash中所有map
        Map<String, String> user = jedis.hgetAll("user");
        for (Map.Entry<String, String> entry : user.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
        }
        //3.关闭连接
        jedis.close();
    }

    /**
     * list 数据类型操作
     */
    @Test
    public void test4() {
        Jedis jedis = new Jedis();
        jedis.del("mylist");

        //从左边存
        jedis.lpush("mylist", "a", "b", "c");
        //从右边存
        jedis.rpush("mylist", "a", "b", "c");

        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        //list 弹出
        String element1 = jedis.lpop("mylist");
        System.out.println(element1);

        String element2 = jedis.rpop("mylist");
        System.out.println(element2);

        jedis.close();
    }

    /**
     * sortedset 数据类型操作
     */
    @Test
    public void test6() {
        Jedis jedis = new Jedis();

        jedis.zadd("mysortedset", 3, "提莫");
        jedis.zadd("mysortedset", 66, "亚索");
        jedis.zadd("mysortedset", 3, "猴子");

        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);

        jedis.close();
    }

    /**
     * set 数据类型操作
     */
    @Test
    public void test5() {
        Jedis jedis = new Jedis();

        jedis.sadd("myset", "java", "python", "c++");

        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);

        jedis.close();
    }

    /**
     * Jeis连接池的使用
     */
    @Test
    public void test7() {
        //0.创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        //1.创建Jedis连接池对象
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);

        //2.获取连接
        Jedis jedis = jedisPool.getResource();
        //3.使用
        jedis.set("hehe", "哈哈");
        //4.关闭 归还到连接池中
        jedis.close();
    }

    /**
     * Jeis连接池工具类的使用
     */
    @Test
    public void test8() {
        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();

        jedis.set("hehe", "lalala");
        //4.关闭 归还到连接池中
        jedis.close();
    }
}
