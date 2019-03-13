package com.beta.sb.thread.test1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Created by yaoyt on 2019-03-13.
 *
 * @author yaoyt
 */
public class Run1 implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(Run1.class);

    @Override
    public void run() {
        int count = 0;
        for (int i = 0; i < 20; i++) {
            count++;
            logger.info("--------2222--------{}", count);
            if (count == 10) {
                //System.out.println(1 / 0);
            }
            if (count == 20) {
                logger.info("count={}", count);
                break;
            }
        }
        logger.info("线程执行结束");

        //while (true) {
            //try {

           // } catch (Exception e) {
           //     e.printStackTrace();
           // }
        //}
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("========111======");
            }
        });
        TimeUnit.SECONDS.sleep(5);
        executorService.execute(new Run1());

    }
}
