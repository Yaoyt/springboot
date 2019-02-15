package com.beta.sb.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by yaoyt on 17/5/9.
 *
 * @author yaoyt
 */
@Component
public class ImpalaIdGenerator {

    private final Logger log = LoggerFactory.getLogger(ImpalaIdGenerator.class);

    private static long sequenceNumber = 1000L;

    private static long staticCurrentTimeMills ;

    private static long staticCurrentSequenceNumber;

    /**
     * 方法的功能描述: 生成Impala中的唯一id
     * 规则: 当前的秒数 + application.properties中定义的serviceNumber + 6位的sequence
     *@author yaoyt
     *@time 17/5/9 上午11:03
     */
    public Long getUniqueId(){
        long currentTimeMills = System.currentTimeMillis();
        // 判断当前时间戳是否有用过
        // 如果当前时间戳,未被使用,则当前时间戳下的sequenceNumber 置为初始值
        if(currentTimeMills != staticCurrentTimeMills){
            staticCurrentTimeMills = currentTimeMills;
            staticCurrentSequenceNumber = sequenceNumber;
        // 如果当前戳已经被使用过,则拿到当前的sequenceNumber +1 作为sq
        }else{
            staticCurrentSequenceNumber = staticCurrentSequenceNumber + 1;
        }
        StringBuffer uniqueId = new StringBuffer();
        uniqueId.append(currentTimeMills).append("11").append(staticCurrentSequenceNumber);
        return Long.valueOf(uniqueId.toString());
    }
}
