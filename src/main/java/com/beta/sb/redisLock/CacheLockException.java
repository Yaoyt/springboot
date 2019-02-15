package com.beta.sb.redisLock;

/**
 * Created by yaoyt on 19/2/15.
 *
 * @author yaoyt
 */
public class CacheLockException extends RuntimeException {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CacheLockException(String msg) {
        this.msg = msg;
    }

    public CacheLockException() {
    }

}
