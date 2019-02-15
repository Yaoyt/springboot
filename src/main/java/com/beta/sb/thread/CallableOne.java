package com.beta.sb.thread;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by yaoyt on 17/6/28.
 *
 * @author yaoyt
 */
public class CallableOne implements Callable<Object> {
    private String taskNum;

    public CallableOne(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}
