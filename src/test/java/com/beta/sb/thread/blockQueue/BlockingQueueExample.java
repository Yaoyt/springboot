package com.beta.sb.thread.blockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yaoyt on 2019-03-15.
 *
 * @author yaoyt
 */
public class BlockingQueueExample {

    public static void main(String[] args) throws Exception{
        BlockingQueue queue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
}
