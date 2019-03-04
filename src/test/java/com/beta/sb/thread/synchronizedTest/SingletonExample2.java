package com.beta.sb.thread.synchronizedTest;

/**
 * Created by yaoyt on 19/2/27.
 * 饿汉模式
 * 单例实例在类装载时进行创建
 *
 * @author yaoyt
 */
public class SingletonExample2 {
    // 构造函数私有化
    private SingletonExample2(){}
    // 类加载时创建,线程安全.
    private static SingletonExample2 instance = new SingletonExample2();
    // 静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
