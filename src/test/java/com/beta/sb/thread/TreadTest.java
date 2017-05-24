package com.beta.sb.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
public class TreadTest {

    private static final CountDownLatch latch=new CountDownLatch(2);//两个工人的协作

    public static void main(String[] args) throws InterruptedException {
        final List<String> list = new ArrayList<String>();

        Thread t1 = new Thread(){
            public void run() {
                list.add("t1");
                latch.countDown();
            }
        };
        t1.start();


        Thread t2 = new Thread(){
            public void run() {
                list.add("t2");
                latch.countDown();
            }
        };
        t2.start();

        latch.await();

        for (String s : list) {
            System.out.println("s="+s);
        }
    }

}
