package com.beta.sb.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
public class CyclicBarrierTest {

    public static void main(String[] args)  throws  Exception{
        final CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < 4; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(  "子线程执行!");
                    try {
                        barrier.await();// 到达屏障
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        barrier.await();
        System.out.println("主线程执行!");
    }
}
