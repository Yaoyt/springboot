package com.beta.sb.proxy;

import org.junit.Test;

/**
 * Created by yaoyt on 19/2/15.
 *
 * @author yaoyt
 */
public class ProxyTest {


    @Test
    public void test1(){
        LiuDeHuaProxy proxy = new LiuDeHuaProxy();
        // 获得代理对象
        Person p = proxy.getProxy();
        // 调用代理对象的sing方法
        String retValue = p.sing("冰雨");
        System.out.println(retValue);
        //调用代理对象的dance方法
        String value = p.dance("江南style");
        System.out.println(value);
    }
}
