package com.cumt.config.redis;

import com.cumt.cache.JedisPoolWriter;
import com.cumt.cache.JedisUtil;
import com.cumt.util.DESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
/**
 * 配置 redis
 *
 * @author draymonder
 */
@Configuration
public class RedisConfiguration {
    @Value("${redis.hostname}")
    private String hostname;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.pool.maxActive}")
    private int maxActive;
    @Value("${redis.pool.maxIdle}")
    private int maxIdle;
    @Value("${redis.pool.maxWait}")
    private long maxWaitMillis;
    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private JedisPoolWriter jedisPoolWriter;

    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 创建 redis 连接池的设置
     *
     * @return
     */
    @Bean(name="jedisPoolConfig")
    public JedisPoolConfig createJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最多的jedis实例
        jedisPoolConfig.setMaxIdle(maxActive);
        // 空闲maxIdle个链接
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 最大等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 检查有效性
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    /**
     * 创建 redis 工具类，封装好 redis 的连接以进行相关操作
     * @return
     */
    @Bean(name = "jedisUtil")
    public JedisUtil createJedisUtil() {
        JedisUtil jedisUtil = new JedisUtil();
        jedisUtil.setJedisPool(jedisPoolWriter);
        return jedisUtil;
    }

    /**
     * 创建 redis 连接池
     *
     * @return
     */
    @Bean(name="jedisWritePool")
    public JedisPoolWriter createJedisPoolWriter() {
        return new JedisPoolWriter(jedisPoolConfig, hostname, port, timeout,
                DESUtil.getDecryptString(password));
    }

    /**
     * redis的key操作
     */
    @Bean(name="jedisKeys")
    public JedisUtil.Keys createJedisKey() {
        return jedisUtil.new Keys();
    }

    /**
     * redis的String操作
     */
    @Bean(name="jedisStrings")
    public JedisUtil.Strings createJedisStrings() {
        return jedisUtil.new Strings();
    }

    /**
     * redis的Lists操作
     */
    @Bean(name="jedisLists")
    public JedisUtil.Lists createJedisLists() {
        return jedisUtil.new Lists();
    }

    /**
     * redis的HashMap操作
     */
    @Bean(name="jedisHash")
    public JedisUtil.Hash createJedisHash() {
        return jedisUtil.new Hash();
    }

    /**
     * redis的Sets操作
     */
    @Bean(name="jedisSets")
    public JedisUtil.Sets createJedisSets() {
        return jedisUtil.new Sets();
    }
}
