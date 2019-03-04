package com.beta.sb.thread;

import com.google.common.util.concurrent.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yaoyt on 19/2/21.
 *
 * @author yaoyt
 */
public class ListenableFutureTest {

    // 线程池中的个数
    private static final int POOL_SIZE = 50;
    // 带有回调机制的线程池
    private static final ListeningExecutorService SERIVCE = MoreExecutors.listeningDecorator(
            new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(256), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy())
    );

    private static Logger LOG = LoggerFactory.getLogger(ListenableFutureTest.class);

    @Test
    public void test1(){
        final List<String> value = Collections.synchronizedList(new ArrayList<String>());
        try {
            List<ListenableFuture<String>> futures = new ArrayList<ListenableFuture<String>>();
            // 将实现了callable的任务放入到线程池中,得到一个带有回调机制的ListenableFuture实例,
            // 通过Futures.addCallback方法对得到的ListenableFuture实例进行监听，一旦得到结果就进入到onSuccess方法中，
            // 在onSuccess方法中将查询的结果存入到集合中
            for (int i = 0; i < 10; i ++) {
                final int index = i;
                if (i == 9) {
                    Thread.sleep(500 * i);
                }
                ListenableFuture<String> sfuture = SERIVCE.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        long time = System.currentTimeMillis();
                        LOG.info("Finishing sleeping task{}: {}", index, time);
                        return String.valueOf(time);
                    }
                });
                sfuture.addListener(new Runnable() {
                    @Override
                    public void run() {
                        LOG.info("Listener be triggered for task{}.", index);
                    }
                }, SERIVCE);

                Futures.addCallback(sfuture, new FutureCallback<String>() {
                    public void onSuccess(String result) {
                        LOG.info("Add result value into value list {}.", result);
                        value.add(result);
                    }

                    public void onFailure(Throwable t) {
                        LOG.info("Add result value into value list error.", t);
                        throw new RuntimeException(t);
                    }
                }, SERIVCE);
                // 将每一次查询得到的ListenableFuture放入到集合中
                futures.add(sfuture);
            }
            // 这里将集合中的若干ListenableFuture形成一个新的ListenableFuture
            // 目的是为了异步阻塞，直到所有的ListenableFuture都得到结果才继续当前线程
            // 这里的时间取的是所有任务中用时最长的一个
            ListenableFuture<List<String>> allAsList = Futures.allAsList(futures);
            allAsList.get();
            LOG.info("All sub-task are finished.");
        } catch (Exception e) {

        }
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        // case1: supplyAsync
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            LOG.info("Run supplyAsync.");
            return "Return result of Supply Async";
        });
        // case2: thenRun，与supplyAsync同线程
        future.thenRun(new Runnable() {
            @Override
            public void run() {
                LOG.info("Run action.");
            }
        });
        // case2: thenRunAsync，另启动线程执行
        future.thenRunAsync(new Runnable() {
            @Override
            public void run() {
                LOG.info("Run async action.");
            }
        });

        // 主动触发Complete结束方法
        // future.complete("Manual complete value.");
        future.whenComplete((v, e) -> {
            LOG.info("WhenComplete value: " + v);
            LOG.info("WhenComplete exception: " + e);
        });
        LOG.info(future.get());

        /*CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            LOG.info("Return result of Run Async.");
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "hello";
        });
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            return "world";
        });
        CompletableFuture<String> f = future3.thenCombine(future4,
                (x, y) -> x + "-" + y);
        LOG.info(f.get());*/


    }

    @Test
    public void test11(){
        //使用 guava 开源框架的 ThreadFactoryBuilder 给线程池的线程设置名字
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-thread-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(4, 10, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(256),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        pool.shutdown();
    }
}
