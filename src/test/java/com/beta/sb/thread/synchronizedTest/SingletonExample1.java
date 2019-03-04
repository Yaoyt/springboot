package com.beta.sb.thread.synchronizedTest;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Created by yaoyt on 19/2/27.
 * 懒汉模式
 * 单例实例在第一次使用时进行创建.
 * 这种是线程不安全的
 * @author yaoyt
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有构造函数,不允许外部创建
     */
    private SingletonExample1(){

    }

    /**
     * 单例对象
     */
    private static SingletonExample1 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample1 getInstance(){
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
