package com.beta.sb.web.rest;

import com.beta.sb.common.MyException;
import com.beta.sb.service.retry.RetryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaoyt on 17/5/5.
 *
 * @author yaoyt
 */
@RestController
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);


    @Autowired
    private RetryService retryService;

    @RequestMapping("/hello")
    public String index() throws Exception {
        //retryService.call();
        logger.trace("spring boot logger trace 输出");
        logger.info("spring boot logger info 日志输出 ");
        logger.warn("spring boot logger warn 输出");
        logger.error("spring boot logger error 输出");
        return "Hello World";
    }

    @RequestMapping("/hello2")
    public String index2() {
        return "中国";
    }

    @RequestMapping("/hello3")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
}
