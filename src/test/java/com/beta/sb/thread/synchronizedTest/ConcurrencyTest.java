package com.beta.sb.thread.synchronizedTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yaoyt on 19/2/27.
 *
 * @author yaoyt
 */
public class ConcurrencyTest {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;
    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    public static int count = 0;

    /**
     * 工作内存
     */
    public static AtomicLong count2 = new AtomicLong(0);


    private static final Logger log = LoggerFactory.getLogger(ConcurrencyTest.class);

    /**
     *方法的功能描述:
     *  //当前方法是非线程安全的,每次运行结果不一致.
     *@author yaoyt
     *@time 19/2/27 上午10:00
     */
    @Test
    public void test1() throws InterruptedException {
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量, 给出允许并发的线程数目
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 统计计数结果
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        // 将请求放入线程池
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    //信号量的获取
                    semaphore.acquire();
                    add();
                    //释放
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        log.info("count:{}", count);

    }

    /**
     *方法的功能描述:
     *  将上一个方法中的 ++ 调用改为 atomic, 则变为线程安全.
     *@author yaoyt
     *@time 19/2/27 上午10:04
     */
    @Test
    public void test2() throws Exception{
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量, 给出允许并发的线程数目
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 统计计数结果
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        // 将请求放入线程池
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    //信号量的获取
                    semaphore.acquire();
                    add2();
                    //释放
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        log.info("count:{}", count2);
    }


    /**
     * 统计方法
     */
    private synchronized static void add() {
        count++;
    }

    private static void add2() {
        count2.incrementAndGet();
        // count.getAndIncrement();
    }


}
