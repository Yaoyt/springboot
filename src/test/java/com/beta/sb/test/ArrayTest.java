package com.beta.sb.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by yaoyt on 17/6/7.
 *
 * @author yaoyt
 */
public class ArrayTest {

    @Test
    public void test1(){
        Object[] a = {1920,"123.59.67.236",null,1080};
        Object[] b = {1920,"123.59.67.236",null,1080};
        System.out.println(a.equals(b));
        List<Object> l1 = Lists.newArrayList();
        l1.add(1920);
        l1.add("123.59.67.236");
        l1.add(null);
        l1.add(1080);
        List<Object> l2 = Lists.newArrayList();
        l2.add(1920);
        l2.add("123.59.67.236");
        l2.add(null);
        l2.add(1080);
        System.out.println(l1.equals(l2));
    }

    @Test
    public void test2(){
        String[] a1 = {"张三", "李四", "王五"};
        List<String> headerList = Lists.newArrayList(a1);
        for (String str : headerList) {
            System.out.println(str);
        }
    }
}
