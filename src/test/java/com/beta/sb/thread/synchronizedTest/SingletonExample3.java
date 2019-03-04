package com.beta.sb.thread.synchronizedTest;

/**
 * Created by yaoyt on 19/2/27.
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * @author yaoyt
 */
public class SingletonExample3 {
    //构造函数私有化
    private SingletonExample3(){}

    //单例对象
    private static SingletonExample3 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    public static SingletonExample3 getInstance(){
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
