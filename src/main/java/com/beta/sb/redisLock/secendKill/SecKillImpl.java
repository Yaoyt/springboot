package com.beta.sb.redisLock.secendKill;

import com.beta.sb.redisLock.LockedObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaoyt on 19/2/15.
 *
 * @author yaoyt
 */
public class SecKillImpl implements SeckillInterface{
    public static Map<Long, Long> inventory ;

    static{
        inventory = new HashMap<>();
        inventory.put(10000001L, 10000L);
        inventory.put(10000002L, 10000L);
    }

    @Override
    public Object secKill(String userID, @LockedObject Long commidityID) {
        //最简单的秒杀，这里仅作为demo示例
        return reduceInventory(commidityID);
    }

    //模拟秒杀操作，姑且认为一个秒杀就是将库存减一，实际情景要复杂的多
    public Long reduceInventory(Long commodityId){
        inventory.put(commodityId,inventory.get(commodityId) - 1);
        return inventory.get(commodityId);
    }




}
