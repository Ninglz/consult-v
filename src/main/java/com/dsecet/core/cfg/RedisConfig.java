package com.dsecet.core.cfg;

import com.dsecet.api.security.SessionToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: lxl
 */
@Configuration
public class RedisConfig{

    @Value("${redis.host}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.pass}")
    private String password;

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.db.default}")
    private int database;

    @Value("${redis.max.connection}")
    private int maxIdle;

    @Value("${redis.max.connection}")
    private long maxWait;

    @Value("${redis.max.free}")
    private long maxFree;

    @Value("${redis.pool.use}")
    private boolean usePool;

    @Value("${redis.test.on.borrow}")
    private boolean testOnBorrow;

    @Bean(name="jedisPoolConfig")
    public JedisPoolConfig configJedisPoolConfig(){
        JedisPoolConfig config =  new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(testOnBorrow);
        return config;
    }

    @Bean(name="jedisConnectionFactory",destroyMethod="destroy")
    public JedisConnectionFactory configJedisConnectionFactory(){
        JedisConnectionFactory config =  new JedisConnectionFactory(configJedisPoolConfig());
        config.setHostName(hostName);
        config.setPort(port);
        config.setPassword(password);
        config.setTimeout(timeout);
        config.setDatabase(database);
        config.setUsePool(usePool);
        config.afterPropertiesSet();

        return config;
    }

    @Bean(name="redisTemplate")
    public RedisTemplate configStringRedisTemplate(){
        RedisTemplate config =  new RedisTemplate();
        config.setConnectionFactory(configJedisConnectionFactory());
        config.setKeySerializer(new StringRedisSerializer());
        config.setValueSerializer(new StringRedisSerializer());
        return config;
    }


}
