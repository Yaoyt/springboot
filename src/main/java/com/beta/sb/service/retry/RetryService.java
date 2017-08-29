package com.beta.sb.service.retry;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * Created by yaoyt on 17/8/28.
 *
 * @author yaoyt
 */
@Service
public class RetryService {

    @Retryable(value= {RemoteAccessException.class},maxAttempts = 3,backoff = @Backoff(delay = 5000l,multiplier = 2))
    public void call() throws Exception{
        System.out.println(" do something ....");
        throw new RemoteAccessException("RPC调用异常");
    }

    @Recover
    public void recover(RemoteAccessException e) {
        System.out.println(e.getMessage());
    }
}
