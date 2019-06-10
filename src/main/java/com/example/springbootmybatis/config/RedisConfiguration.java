package com.example.springbootmybatis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {
    private Logger LOGGER = LoggerFactory.getLogger(RedisConfiguration.class);
    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private int redisPort;
    @Value("${redis.max-idle}")
    private int redisMaxIdle;
    @Value("${redis.maxTotal}")
    private int redisMaxTotal;
    @Value("${redis.database}")
    private int database;
    @Value("${redis.max-active}")
    private int maxActive;
    @Value("${redis.max-wait}")
    private int maxWait;
    @Value("${redis.min-idle}")
    private int minIdle;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.test-on-create}")
    private boolean testOnCreate;
    @Value("${redis.test-on-borrow}")
    private boolean testOnBorrow;
    @Value("${redis.test-on-return}")
    private boolean testOnReturn;
    @Value("${redis.test-while-idle}")
    private boolean testWhileIdle;
    @Value("${redis.pass-word}")
    private String passWord;

    @Bean
    public JedisPool jedisPoolFactory(){
        LOGGER.info("JedisPool注入成功......");
        LOGGER.info("Jedis地址："+redisHost);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxIdle(redisMaxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnCreate(testOnCreate);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,redisHost,redisPort,timeout,passWord,database);
        return jedisPool;
    }

    @Bean
    public JedisPoolConfig getJedisConfig(){
        LOGGER.info("JedisPoolConfig注入成功......");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxIdle(redisMaxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnCreate(testOnCreate);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        LOGGER.info("JedisConnectionFactory注入成功......");
        JedisPoolConfig jedisPoolConfig = getJedisConfig();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.setHostName(redisHost);
        jedisConnectionFactory.setPort(redisPort);
        jedisConnectionFactory.setPassword(passWord);
        jedisConnectionFactory.setDatabase(database);
        return jedisConnectionFactory;
    }
    @Bean(name = "redisTemplate")
    public RedisTemplate<Object,Object> redisTemplate(){
        LOGGER.info("RedisTemplate注入成功......");
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        //设置value序列化规则和key的序列化规则
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }
}
