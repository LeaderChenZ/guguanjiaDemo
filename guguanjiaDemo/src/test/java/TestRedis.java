import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author Chen
 * @description
 * @date 2019/11/26 09
 */
public class TestRedis {

    @Test
    public void testJedisConnect() {
        @SuppressWarnings("resource")
        Jedis jedis = new Jedis("localhost");
        jedis.auth("123456");
        System.out.println("连接redis服务端成功！");

        System.out.println("redis服务器正在运行嘛？"+jedis.ping());
        System.out.println("redis服务器信息？\n"+jedis.info());
    }

    @Test
    public void testKey() {
        @SuppressWarnings("resource")
        Jedis jedis = new Jedis("localhost");
        jedis.auth("123456");
        System.out.println("连接redis服务端成功！");
        System.out.println("redis服务器正在运行嘛？"+jedis.ping());

        jedis.set("jedisKey","One");
        String jedisKey = jedis.get("jedisKey");


        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

    @Test
    public void testSpringRedis(){

    }
}
