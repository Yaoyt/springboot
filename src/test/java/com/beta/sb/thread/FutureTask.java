package com.beta.sb.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
public class FutureTask {
    private static Logger logger = LoggerFactory.getLogger(FutureTask.class);

    private static final ExecutorService executor = Executors.newCachedThreadPool();
    private static Random random = new Random();
    private static long timeout = 12L;

    /**
     * 启动多个子任务
     */
    public static void startMoreTask() {
        List<Callable<Integer>> subTasks = new ArrayList<Callable<Integer>>(); // 子任务集合
        List<Integer> subTaskResult = new ArrayList<Integer>(); // 子任务的返回集合

        // 1.初始化10个子任务
        for (int i = 1; i <= 10; i ++) {
            SubTask subTask = new SubTask("子线程-" + i, random.nextInt(10)); // 子线程随机在10秒内完成
            subTasks.add(subTask);
        }

        // 2.执行所有的子任务
        try {

            List<Future<Integer>> futures = executor.invokeAll(subTasks);

            for (Future<Integer> future : futures) {
                try {

                    Integer result = future.get(timeout, TimeUnit.SECONDS); // 设置每个子任务的执行时间不得超过4秒
                    subTaskResult.add(result);
                } catch (ExecutionException | TimeoutException e) {
                    future.cancel(true); // 当出现执行异常和超时异常时，终止该子任务
                }
            }
        } catch (InterruptedException e) {
            logger.info("任务执行异常：" + e.getMessage());
        }
    }


    public static void main(String[] args) {
        // subTask1测试超时的情况
        SubTask subTask1 = new SubTask("子线程 - 1", 10);
        Future<Integer> future1 = executor.submit(subTask1);
        Integer result1 = null;

        try {

            result1 = future1.get(5, TimeUnit.SECONDS); // 设置子任务的执行时间不得超过5秒
        } catch (InterruptedException e) {
            logger.info("线程中断出错");
            future1.cancel(true);// 中断执行此任务的线程
        } catch (ExecutionException e) {
            logger.info("线程服务出错");
            future1.cancel(true);// 中断执行此任务的线程
        } catch (TimeoutException e) {// 超时异常
            logger.info("线程执行超时");
            future1.cancel(true);// 中断执行此任务的线程
        }

        logger.info("subTask1运行结果:" + (result1 == null ? "null" : result1));

        // subTask2测试拿到子线程返回结果的情况
        SubTask subTask2 = new SubTask("子线程 - 2", 5);
        Future<Integer> future2 = executor.submit(subTask2);
        Integer result2 = null;

        try {

            result2 = future2.get(10, TimeUnit.SECONDS); // 设置子任务的执行时间不得超过10秒
        } catch (InterruptedException e) {
            logger.info("线程中断出错");
            future2.cancel(true);// 中断执行此任务的线程
        } catch (ExecutionException e) {
            logger.info("线程服务出错");
            future2.cancel(true);// 中断执行此任务的线程
        } catch (TimeoutException e) {// 超时异常
            logger.info("线程执行超时");
            future2.cancel(true);// 中断执行此任务的线程
        }

        logger.info("subTask2运行结果:" + (result2 == null ? "null" : result2));

        startMoreTask();
    }
}

class SubTask implements Callable<Integer> {

    private static Logger logger = LoggerFactory.getLogger(SubTask.class);

    private String name; // 子线程名
    private int second; // 子线程完成需要的时间（秒）

    public SubTask (String name, int second) {
        this.name = name;
        this.second = second;
    }

    @Override
    public Integer call() throws Exception {
        logger.info("#子线程-" + name + " 开始");
        Thread.sleep(second * 1000L);
        logger.info("#子线程-" + name + " 结束，耗时秒数: " + second);

        return second;
    }
}
