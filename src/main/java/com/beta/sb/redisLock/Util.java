package com.beta.sb.redisLock;

import com.alibaba.fastjson.JSON;

/**
 * Created by yaoyt on 19/2/15.
 *
 * @author yaoyt
 */
public class Util {
    public static  String beanToJson(Object o){
        return JSON.toJSONString(o);
    }
    //parse an object from
    public static <T> T jsonToBean(String json,Class<T> cls){
        return JSON.parseObject(json, cls);
    }
}
