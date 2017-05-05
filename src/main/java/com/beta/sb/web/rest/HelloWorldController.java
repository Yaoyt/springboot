package com.beta.sb.web.rest;

import com.beta.sb.common.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yaoyt on 17/5/5.
 *
 * @author yaoyt
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String index(){
        return "Hello World";
    }

    @RequestMapping("/hello2")
    public String index2(){
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
