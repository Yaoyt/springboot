package com.beta.sb.test;

/**
 * Created by yaoyt on 18/12/5.
 *
 * @author yaoyt
 */
public class SynchronizedTest {
    public static void main(String[] args) throws Exception {
        /*Sy sy = new Sy(0);
        Sy sy2 = new Sy(1);
        sy.start();
        sy2.start();*/
        Syc syc = new Syc();
        Thread t1 = new Thread(new Thread1(syc));
        Thread t2 = new Thread(new Thread1(syc));
        t1.start();
        t2.start();


    }

}

class Syc{

    public synchronized void  test1() throws Exception{
        System.out.println("test1方法开始执行" + System.currentTimeMillis());
        Thread.sleep(5000);
        System.out.println("test1方法执行完成" + System.currentTimeMillis());

    }

    public void test2() throws Exception{
        System.out.println("test2方法开始执行" + System.currentTimeMillis());
        Thread.sleep(2000);
        System.out.println("test2方法执行完成" + System.currentTimeMillis());
    }


}

class Thread1 extends Thread{

    private Syc syc;

    public Thread1(Syc syc) {
        this.syc = syc;
    }

    public void run(){
        try {
            syc.test1();
        } catch (Exception e) {e.printStackTrace();
        }
    }
}

class Thread2 extends Thread{

    private Syc syc;

    public Thread2(Syc syc) {
        this.syc = syc;
    }

    public void run(){
        try {
            syc.test2();
        } catch (Exception e) {e.printStackTrace();
        }
    }
}




class Sy extends Thread {
    private int flag;
    static Object x1 = new Object();
    static Object x2 = new Object();

    public Sy(int flag) {
        this.flag = flag;
    }
    @Override
    public void run(){
        System.out.println(flag);
        try {
            if (flag == 0) {
                synchronized (x1) {
                    System.out.println(flag+"锁住了x1");
                    Thread.sleep(1000);
                    synchronized (x2) {
                        System.out.println(flag+"锁住了x2");
                    }
                    System.out.println(flag+"释放了x1和x2");
                }
            }
            if(flag == 1) {
                synchronized (x2) {
                    System.out.println(flag+"锁住了x2");
                    Thread.sleep(1000);
                    synchronized (x1) {
                        System.out.println(flag+"锁住了x1");
                    }
                    System.out.println(flag+"释放了x1和x2");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}