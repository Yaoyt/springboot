package com.beta.sb.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
@Component
public class AsyncTask {

    private Logger log = LoggerFactory.getLogger(AsyncTask.class);

    @Async("mySimpleAsync")
    public Future<String> doTask1() throws InterruptedException{
        log.info("Task1 started.");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();

        log.info("Task1 finished, time elapsed: {} ms.", end-start);

        return new AsyncResult<>("Task1 accomplished!");
    }

    @Async("myAsync")
    public Future<String> doTask2() throws InterruptedException{
        log.info("Task2 started.");
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();

        log.info("Task2 finished, time elapsed: {} ms.", end-start);

        return new AsyncResult<>("Task2 accomplished!");
    }

    @Async("mySimpleAsync")
    public Future<String> doTask3(CountDownLatch latch, String str) throws InterruptedException{
        log.info("Task3 started.");
        long start = System.currentTimeMillis();
        Thread.sleep(6000);
        long end = System.currentTimeMillis();

        log.info("Task3 finished, time elapsed: {} ms.", end-start);
        latch.countDown();
        return new AsyncResult<>(str);
    }
}
