package com.beta.sb.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
public class FixedThreadPoolTest {


    @Test
    public void test1() throws Exception{
        // 创建线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10));
        // 向线程池中加入执行线程
        for (int i = 0; i < 30; i++) {
            Runnable runnable = new TaskWithoutResult(i);
            poolExecutor.submit(runnable);
        }
        //poolExecutor.shutdown();
        Thread.sleep(10000000L);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(20);
        List<Future<String>> futures = new ArrayList<Future<String>>(10);


        for(int i = 0; i < 10; i++){
            futures.add(pool.submit(new StringTask(i)));
        }

        for(Future<String> future : futures){
            String result = future.get();
            //Compute the result
            System.out.println(result);
        }

        pool.shutdown();
    }
}


class TaskWithoutResult implements Runnable {

    private int threadName;

    public TaskWithoutResult(int threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println("线程" + Thread.currentThread() + " -" + threadName + "开始运行");
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("线程" + Thread.currentThread() + " -" + threadName + "运行结束");


    }
}
