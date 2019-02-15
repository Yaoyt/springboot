package com.beta.sb.redisLock;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;

/**
 * Created by yaoyt on 19/2/15.
 *
 * @author yaoyt
 */
public class RedisFactory {

    public static JedisPoolConfig getPoolConfig() throws IOException {

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(3000);
            config.setMinIdle(3000);
            config.setMaxTotal(3000);
            return config;
        } finally {
        }

    }

    public static RedisClient getDefaultClient(){
        JedisPool pool = new JedisPool("192.168.1.54");
        RedisClient client = new RedisClient(pool);
        return client;
    }

}
