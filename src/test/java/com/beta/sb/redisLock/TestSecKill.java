package com.beta.sb.redisLock;

import com.beta.sb.redisLock.secendKill.SecKillImpl;
import com.beta.sb.redisLock.secendKill.SeckillInterface;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yaoyt on 19/2/15.
 *
 * @author yaoyt
 */
public class TestSecKill {

    private static Long commidityId1 = 10000001L;
    private static Long commidityId2 = 10000002L;

    @Test
    public void test1(){
        int threadCount = 1000;
        int splitPoint = 500;
        //子线程的信号量
        CountDownLatch endCount = new CountDownLatch(threadCount);
        //主线程的信号量
        CountDownLatch beginCount = new CountDownLatch(1);
        SecKillImpl testClass = new SecKillImpl();
        Thread[] threads = new Thread[threadCount];

        //起500个线程，秒杀第一个商品
        for (int i = 0; i < splitPoint; i ++) {
            threads[i] = new Thread(new  Runnable() {
                public void run() {
                    try {
                        //在子线程中,等待主线程释放信号量,不释放不执行
                        beginCount.await();
                        //用动态代理的方式调用secKill方法
                        SeckillInterface proxy = (SeckillInterface) Proxy.newProxyInstance(SeckillInterface.class.getClassLoader(),
                                new Class[]{SeckillInterface.class}, new CacheLockInterceptor(testClass));
                        proxy.secKill("test", commidityId1);
                    } catch (CacheLockException e) {
                        //e.printStackTrace();
                        System.out.println("获取锁出现异常,需要回滚");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        //子线程执行成功后, 将子线程信号量减1
                        endCount.countDown();
                    }
                }
            });
            threads[i].start();
        }
        // 起500个线程,秒杀第二个商品
        for(int i= splitPoint;i < threadCount;i++){
            threads[i] = new Thread(new  Runnable() {
                public void run() {
                    try {
                        //在子线程中,等待主线程释放信号量,不释放不执行
                        beginCount.await();
                        SeckillInterface proxy = (SeckillInterface) Proxy.newProxyInstance(SeckillInterface.class.getClassLoader(),
                                new Class[]{SeckillInterface.class}, new CacheLockInterceptor(testClass));
                        proxy.secKill("test", commidityId2);
                    } catch (CacheLockException e) {
                        //e.printStackTrace();
                        System.out.println("获取锁出现异常,需要回滚");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        //子线程执行成功后, 将子线程信号量减1
                        endCount.countDown();
                    }
                }
            });
            threads[i].start();

        }
        long startTime = System.currentTimeMillis();
        //主线程释放开始信号量，此时,所有的子线程得到通知,开始执行
        beginCount.countDown();
        try {
            //主线程等待 所有的子线程执行完成,并释放 结束信号量
            endCount.await();
            //观察秒杀结果是否正确
            System.out.println(SecKillImpl.inventory.get(commidityId1));
            System.out.println(SecKillImpl.inventory.get(commidityId2));
            System.out.println("error count" + CacheLockInterceptor.ERROR_COUNT);
            System.out.println("total cost " + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        int threadCount = 2000;
        int splitPoint = 1000;
        CountDownLatch endCount = new CountDownLatch(threadCount);
        CountDownLatch beginCount = new CountDownLatch(1);
        SecKillImpl testClass = new SecKillImpl();
        Thread[] threads = new Thread[threadCount];

        //起500个线程，秒杀第一个商品
        for (int i = 0; i < splitPoint; i ++) {
            threads[i] = new Thread(new  Runnable() {
                public void run() {
                    try {
                        //等待在一个信号量上，挂起
                        beginCount.await();
                        testClass.secKill("test", commidityId1);
                        endCount.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
        // 起500个线程,秒杀第二个商品
        for(int i= splitPoint;i < threadCount;i++){
            threads[i] = new Thread(new  Runnable() {
                public void run() {
                    try {
                        //等待在一个信号量上，挂起
                        beginCount.await();
                        testClass.secKill("test", commidityId2);
                        endCount.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();

        }
        long startTime = System.currentTimeMillis();
        //主线程释放开始信号量，并等待结束信号量
        beginCount.countDown();
        try {
            //主线程等待结束信号量
            endCount.await();
            //观察秒杀结果是否正确
            System.out.println(SecKillImpl.inventory.get(commidityId1));
            System.out.println(SecKillImpl.inventory.get(commidityId2));
            System.out.println("error count" + CacheLockInterceptor.ERROR_COUNT);
            System.out.println("total cost " + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
