package com.beta.sb.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by yaoyt on 19/2/27.
 *
 * @author yaoyt
 */
public class AtomicTest {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    private static Logger log = LoggerFactory.getLogger(AtomicTest.class);

    @Test
    public void test1(){
        // 2
        count.compareAndSet(0, 2);
        // no
        count.compareAndSet(0, 1);
        // no
        count.compareAndSet(1, 3);
        // 4
        count.compareAndSet(2, 4);
        // no
        count.compareAndSet(3, 5);
        log.info("count:{}", count.get());
    }

    private static AtomicIntegerFieldUpdater<AtomicTest> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicTest.class, "count2");

    public volatile int count2 = 100;

    @Test
    public void test2(){
        AtomicTest example = new AtomicTest();
        if (updater.compareAndSet(example, 100, 120)) {
            log.info("update success 1, {}",example.count2 );
        }
        if (updater.compareAndSet(example, 100, 120)) {
            log.info("update success 1, {}", example.count2);
        } else {
            log.info("update failed, {}", example.count2);
        }
    }

}
