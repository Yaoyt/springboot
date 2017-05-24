package com.beta.sb.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoyt on 17/5/16.
 *
 * @author yaoyt
 */
public class MapTest {

    @Test
    public void test1(){

        Map<Long, List<Object>> map = Maps.newHashMap();
        List<Object> list = Lists.newArrayList();
        list.add(123);
        map.put(1L, list);
        System.out.println(JSON.toJSONString(map));
        list.add(456);
        System.out.println(JSON.toJSONString(map));
        test2(map);
        System.out.println(map);
    }

    public void test2(Map<Long, List<Object>> map ){
        List<Object> list = map.get(1L);
        list.add(789);

    }

    @Test
    public void test3(){
        long b = new A().a;
        System.out.println(b);
    }

    class A {
        public Long a ;
    }
}
