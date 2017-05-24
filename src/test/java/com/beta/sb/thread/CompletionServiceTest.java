package com.beta.sb.thread;

import java.util.concurrent.*;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
public class CompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        CompletionService<String> pool = new ExecutorCompletionService<String>(threadPool);
        for(int i = 0; i < 10; i++){
            pool.submit(new StringTask(i));
        }

        for(int i = 0; i < 10; i++){
            String result = pool.take().get();

            //Compute the result
            System.out.println(result);
        }

        threadPool.shutdown();


    }
}
