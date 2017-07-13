package com.beta.sb.thread;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaoyt on 17/6/28.
 *
 * @author yaoyt
 */
public class ExecutorTest {

    @Test
    public void test1() throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(10);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("task over");
            }
        };
        executor.execute(runnable);

        executor = Executors.newScheduledThreadPool(10);
        ScheduledExecutorService scheduler = (ScheduledExecutorService) executor;
        scheduler.scheduleAtFixedRate(runnable, 10, 10, TimeUnit.SECONDS);
        Thread.sleep(20000);

    }
}
