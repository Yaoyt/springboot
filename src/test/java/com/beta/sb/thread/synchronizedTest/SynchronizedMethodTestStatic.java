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
public class SynchronizedMethodTestStatic {
    // synchronized 如果修饰的是静态方法 或者修饰的 是一个类, 则作用于所有对象,例子
    // 修饰一个静态方法
    public static void test1(int j) {
        synchronized (SynchronizedMethodTestStatic.class) {
            for (int i = 0; i < 10; i ++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 修饰一个静态方法
    public static synchronized void test2(int j){
        for (int i = 0; i < 10; i ++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedMethodTestStatic example1 = new SynchronizedMethodTestStatic();
        SynchronizedMethodTestStatic example2 = new SynchronizedMethodTestStatic();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }
}
