package com.beta.sb.thread;

import java.util.concurrent.Callable;

/**
 * Created by yaoyt on 17/5/23.
 *
 * @author yaoyt
 */
public class StringTask implements Callable<String> {

    private int i ;

    public StringTask(int i) {
        this.i = i;
    }

    public String call(){
        //Long operations
        return "子线程: + " + i;
    }
}
