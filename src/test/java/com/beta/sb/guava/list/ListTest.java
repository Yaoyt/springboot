package com.beta.sb.guava.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
public class ListTest {

    @Test
    public void test1() {
        List<Integer> list = Lists.newArrayList();
        for(int i = 0; i < 100; i ++) {
            list.add(i);
        }
        List<List<Integer>> parts = Lists.partition(list, 10);
        for(int i = 0; i < parts.size(); i ++) {
            System.out.println(JSON.toJSONString(parts.get(i)));
        }
    }
}
