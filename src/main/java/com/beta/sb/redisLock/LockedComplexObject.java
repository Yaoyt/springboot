package com.beta.sb.redisLock;

import java.lang.annotation.*;

/**
 * Created by yaoyt on 19/2/15.
 * LockedComplexObject也是参数级的注解，用于注解自定义类型的参数
 *
 * @author yaoyt
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedComplexObject {
    // 含有成员变量的复杂对象中需要加锁的成员变量
    // 如一个商品对象的商品ID
    String field() default "";
}
