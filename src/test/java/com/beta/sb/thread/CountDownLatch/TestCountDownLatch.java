package com.beta.sb.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yaoyt on 2019-03-15.
 *
 * @author yaoyt
 */
public class TestCountDownLatch {

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);

        Waiter      waiter      = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        new Thread(waiter).start();
        new Thread(decrementer).start();

        Thread.sleep(4000);
    }
}
