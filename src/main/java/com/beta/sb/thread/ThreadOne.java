package com.beta.sb.thread;

/**
 * Created by yaoyt on 17/6/28.
 *
 * @author yaoyt
 */
public class ThreadOne extends Thread {
    private int i = 0;

    public void run(){
        for ( i = 0; i < 10; i ++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}
