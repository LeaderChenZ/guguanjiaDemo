package com.dfbz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Chen
 * @description
 * @date 2019/11/26 12
 */
@Configuration
@PropertySource(value = "classpath:redis.properties", encoding = "utf-8")
public class SpringRedis {


    @Value("${host}")
    private String host;

    @Value("${password}")
    private String password;

    @Value("${port}")
    private int port;

    @Value("${maxIdle}")
    private int maxIdle;

    @Value("${minIdle}")
    private int minIdle;


    //1，配置连接redis数据源
    @Bean
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);//设置主机IP
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);

        //jedis的连接池配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        jedisConnectionFactory.setTimeout(0);
        jedisConnectionFactory.setPoolConfig(config);
        return jedisConnectionFactory;
    }

    //2.配置RedisTemplate的key和value的序列化策略
    /*
        为了方便管理数据库，在数据库中也可以看到key和value数据的结构，需要自定义序列化策略
    */

    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //设置key为非hash的序列化和反序列化策略
        template.setKeySerializer(template.getStringSerializer());
        //设置value为非hash类型的序列化和反序列化策略
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(jsonRedisSerializer);

        //设置hash类型
        template.setHashKeySerializer(template.getStringSerializer());
        template.setHashValueSerializer(jsonRedisSerializer);

        return template;

    }


}
