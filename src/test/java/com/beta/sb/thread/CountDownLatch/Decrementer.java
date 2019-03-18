package com.beta.sb.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yaoyt on 2019-03-15.
 *
 * @author yaoyt
 */
public class Decrementer implements Runnable {

    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("count down -1 ");
            this.latch.countDown();

            Thread.sleep(1000);
            System.out.println("count down -2 ");
            this.latch.countDown();

            Thread.sleep(1000);
            System.out.println("count down -3 ");
            this.latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
