package com.beta.sb.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
public class FixedThreadPoolTest {

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


