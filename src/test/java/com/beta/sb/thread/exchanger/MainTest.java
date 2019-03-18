package com.beta.sb.thread.exchanger;

import java.util.concurrent.Exchanger;

/**
 * Created by yaoyt on 2019-03-18.
 *
 * @author yaoyt
 */
public class MainTest {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 =
                new ExchangerRunnable(exchanger, "A");

        ExchangerRunnable exchangerRunnable2 =
                new ExchangerRunnable(exchanger, "B");

        ExchangerRunnable exchangerRunnable3 =
                new ExchangerRunnable(exchanger, "C");

        ExchangerRunnable exchangerRunnable4 =
                new ExchangerRunnable(exchanger, "D");


        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();
        new Thread(exchangerRunnable3).start();
        new Thread(exchangerRunnable4).start();
    }
}
