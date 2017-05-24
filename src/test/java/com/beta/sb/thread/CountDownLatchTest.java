package com.beta.sb.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
public class CountDownLatchTest {

    public static void main(String[] args)  throws  Exception{
        final CountDownLatch latch = new CountDownLatch(4);
        for (int i = 0; i < 4; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(  "子线程执行!");
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.out.println("主线程执行!");
    }
}
