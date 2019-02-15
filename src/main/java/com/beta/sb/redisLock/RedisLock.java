package com.beta.sb.redisLock;

import java.util.Random;
import java.util.UUID;

/**
 * Created by yaoyt on 19/2/15.
 *
 * @author yaoyt
 */
public class RedisLock {

    //纳秒和毫秒之间的转换率
    public static final long MILLI_NANO_TIME = 1000 * 1000L;

    public static final String LOCKED = "TRUE";

    public static final Random RANDOM = new Random();
    private String key;
    private String value;
    //封装的操作redis的工具
    private RedisClient redisClient;

    private boolean lock = false;


    /**
     *
     * @param purpose 锁前缀
     * @param key 锁定的ID等东西
     */
    public RedisLock(String purpose, String key){
        this.key = purpose + "_" + key + "_lock";
        this.redisClient = RedisFactory.getDefaultClient();
        this.value = UUID.randomUUID().toString();
    }

    public RedisLock(String purpose, String key,RedisClient client){
        this.key = purpose + "_" + key + "_lock";
        this.redisClient = client;
        this.value = UUID.randomUUID().toString();
    }

    /**
     * 加锁
     * 使用方式为：
     * lock();
     * try{
     * executeMethod();
     * }finally{
     * unlock();
     * }
     *
     * @param timeout,单位:毫秒 timeout的时间范围内轮询锁
     * @param expire  设置锁超时时间
     * @return 成功 or 失败
     */
    public boolean lock(long timeout, int expire) throws CacheLockException {
        long nanoTime = System.nanoTime();
        // 将 毫秒转成纳秒
        timeout *= MILLI_NANO_TIME;
        try {
            // 在timeout的时间范围内不断轮询
            while (System.nanoTime() - nanoTime < timeout) {
                //锁不存在的话, 设置锁并设置锁过期时间,即加锁
                if (this.redisClient.setIfNotExists(key, value, expire)) {
                    this.lock = true;
                    return this.lock;
                }
                System.out.println(Thread.currentThread().getName() + "未获取到锁,出现锁等待");
                //短暂休眠，避免可能的活锁
                Thread.sleep(3, RANDOM.nextInt(30));
                System.out.println(Thread.currentThread().getName() + "锁等待结束,重新获取锁");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheLockException("locking error");
        }
        System.out.println(Thread.currentThread().getName() + "在 " + timeout +" ns内未获取到锁,");
        return false;
    }

    public void unlock(){
        try {
            if(this.lock){
                //redisClient.delKey(key);//直接删除
                redisClient.releaseDistributedLock(key, value);
            }
        } catch (Throwable e) {
        }
    }

    public boolean getLockSta(){
        return this.lock;
    }
}
