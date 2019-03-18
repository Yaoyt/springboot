package com.beta.sb.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yaoyt on 2019-03-15.
 *
 * @author yaoyt
 */
public class Waiter implements Runnable {
    CountDownLatch latch = null;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Waiter Released");
    }
}
