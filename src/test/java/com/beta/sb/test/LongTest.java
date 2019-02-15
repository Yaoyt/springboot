package com.beta.sb.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by yaoyt on 18/7/10.
 *
 * @author yaoyt
 */
public class LongTest {

    @Test
    public void test1(){
        Long l1 = new Long("11");
        Long l2 = new Long("11");
        List<Long> list1 = Lists.newArrayList();
        System.out.println(list1.contains(l2));

        list1.add(l1);
        System.out.println(list1.contains(l2));

    }

    @Test
    public void test3(){
        Long l1 = 145l;
        Long l2 = -451l;
        System.out.println(l1 + (l2));

    }
}
