package com.beta.sb.redisLock;

import java.lang.annotation.*;

/**
 * Created by yaoyt on 19/2/15.
 * lockedObject是参数级的注解，用于注解商品ID等基本类型的参数：
 * @author yaoyt
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {
    // 不需要值
}
