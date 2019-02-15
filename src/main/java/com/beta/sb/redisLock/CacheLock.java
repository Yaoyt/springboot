package com.beta.sb.redisLock;

import java.lang.annotation.*;

/**
 * Created by yaoyt on 19/2/15.
 * cachelock是方法级的注解，用于注解会产生并发问题的方法:
 * @author yaoyt
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
    // redis 锁 key 的前缀
    String lockedPrefix() default "";
    // 轮训锁的时间, 2S
    long timeout() default 200;
    // key在redis中的过期时间,单位毫秒
    int expireTime() default 10000;
}
