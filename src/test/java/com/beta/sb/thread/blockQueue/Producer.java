package com.beta.sb.thread.blockQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by yaoyt on 2019-03-15.
 *
 * @author yaoyt
 */
public class Producer implements Runnable  {

    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put("1");
            Thread.sleep(1000);
            queue.put("2");
            Thread.sleep(1000);
            queue.put("3");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
