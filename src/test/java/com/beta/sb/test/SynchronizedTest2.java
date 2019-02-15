package com.beta.sb.test;

/**
 * Created by yaoyt on 18/12/5.
 *
 * @author yaoyt
 */
public class SynchronizedTest2 {

    /**
     *方法的功能描述:
     *  如果多个线程中传入了同一个对象,
     *  当其中一个线程获得了此对象的锁之后,该锁被释放之前,其他想获取该对象锁的操作都将失败.
     *@author yaoyt
     *@time 18/12/5 下午3:32
     */
    public static void main(String[] args) {
        Object obj = new Object();
        Thread3 t1 = new Thread3(0,obj);
        Thread3 t2 = new Thread3(1,obj);
        Thread3 t3 = new Thread3(2,obj);
        t1.start();
        t2.start();
        t3.start();
    }
}

class Thread3 extends Thread{
    private Object object;
    private int flag;

    public Thread3(int flag,Object object) {
        this.flag = flag;
        this.object = object;
    }

    public void run(){
        if (flag == 0) {
            synchronized (object) {
                System.out.println("执行方法1开始," + System.currentTimeMillis()/1000);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行方法1结束," + System.currentTimeMillis()/1000);
            }
        } else if (flag == 1) {
            System.out.println("执行方法2开始," + System.currentTimeMillis()/1000);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行方法2结束," + System.currentTimeMillis()/1000);
        } else if (flag == 2) {
            synchronized (object) {
                System.out.println("执行方法3开始," + System.currentTimeMillis()/1000);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行方法3结束," + System.currentTimeMillis()/1000);
            }

        }
    }


}


