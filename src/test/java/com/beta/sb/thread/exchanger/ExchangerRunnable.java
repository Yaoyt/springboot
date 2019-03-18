package com.beta.sb.thread.exchanger;

import java.util.concurrent.Exchanger;

/**
 * Created by yaoyt on 2019-03-18.
 *
 * @author yaoyt
 */
public class ExchangerRunnable implements Runnable {

    Exchanger exchanger = null;
    Object    object    = null;

    public ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    @Override
    public void run() {
        try {
            Object previous = this.object;
            this.object = this.exchanger.exchange(this.object);
            System.out.println(
                    Thread.currentThread().getName() +
                            " exchanged " + previous + " for " + this.object
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
