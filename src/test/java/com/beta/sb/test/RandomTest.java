package com.beta.sb.test;

import org.junit.Test;

import java.util.Random;

/**
 * Created by yaoyt on 17/5/25.
 *
 * @author yaoyt
 */
public class RandomTest {

    @Test
    public void test1(){
        for (int i = 0; i < 100; i ++){
            System.out.println(new Random().nextInt(10000000));
        }
    }

    @Test
    public void test2(){
        Integer i = 0;
        add2(i);
        System.out.println(i);
    }


    public void add2(Integer i) {
        i ++;
    }
}
