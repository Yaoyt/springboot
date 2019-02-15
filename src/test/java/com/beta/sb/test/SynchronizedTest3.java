package com.beta.sb.test;

/**
 * Created by yaoyt on 18/12/6.
 *  一般关键词synchronized的用法
        指定加锁对象:对给定对象加锁，进入同步代码前需要获得给定对象的锁。
        直接作用于实例方法:相当于对当前实例加锁，进入同步代码前要获得当前实例的锁。
        直接作用于静态方法:相当于对当前类加锁，进入同步代码前要获得当前类的锁。
    解析:
        步骤2 start 之后,线程进入 runnable 状态,等待CPU调度.由于调用步骤2,需要调用native方法,
        并且在用完成之后在一切准备就绪了，但是并不表示一定在cpu上面执行，有没有真正执行取决服务cpu的调度，
        之后才会调用run方法,因此,这里 步骤3 会先 获得锁, 因此步骤3 先执行.
        步骤2 对应的 m1()方法要等待步骤3的m2()方法执行完成后才会执行.
        当执行步骤4时, 如果 m1 已经将 b 设置为了1000,则 步骤4输出1000, 如果执行步骤4时,m1还没有将b设置为1000,则步骤4输出2000.
        由于m1方法中sleep的存在,因此 b = 会在最后执行,因此结结果为:
        main thread b= 2000 b = 1000 或者 main thread b= 1000 b = 1000

 * @author yaoyt
 */
public class SynchronizedTest3 implements Runnable{

    int b = 100;
    synchronized void m1() throws InterruptedException {
        //System.out.println("步骤m1开始执行+" + System.currentTimeMillis());
        b = 1000;
        Thread.sleep(500); //6
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        System.out.println("步骤m2开始执行+" + System.currentTimeMillis());
        Thread.sleep(600); //5
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest3 lockInstance = new SynchronizedTest3();
        Thread t = new Thread(lockInstance);  //1
        t.start(); //2

        lockInstance.m2(); //3
        System.out.println("main thread b=" + lockInstance.b); //4
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
