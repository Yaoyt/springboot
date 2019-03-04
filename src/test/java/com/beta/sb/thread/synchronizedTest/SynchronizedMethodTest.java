package com.beta.sb.thread.synchronizedTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yaoyt on 19/2/27.
 *
 * @author yaoyt
 */
@Slf4j
public class SynchronizedMethodTest {

    // synchronized 修饰代码块或者修饰 方法时, 锁住的都是实力画后的对象
    // 如果 声明了两个对象,这两个对象之间的调用是互不影响的.
    // synchronized 如果修饰的是静态方法 或者修饰的 是一个类, 则作用于所有对象,例子

    /**
     *方法的功能描述:
     *  修饰一个代码块,作用的也是调用的对象
     *@author yaoyt
     *@time 19/2/27 下午1:41
     */
    public void test1(int j ){
        synchronized (this) {
            for (int i = 0; i < 10; i ++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    /**
     *方法的功能描述
     * 修饰方法, 作用于调用的对象
     *@author yaoyt
     *@time 19/2/27 下午1:43
     */
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i ++) {
            log.info("test2 {} - {}", j, i);
        }
    }




    public static void main(String[] args) {
        SynchronizedMethodTest example1 = new SynchronizedMethodTest();
        SynchronizedMethodTest example2 = new SynchronizedMethodTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }

}
