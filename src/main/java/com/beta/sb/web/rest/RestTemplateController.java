package com.beta.sb.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yaoyt on 17/5/5.
 *
 * @author yaoyt
 */
@RestController()
@RequestMapping("/restTemplate")
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/hello")
    public String index(){
        String quote = restTemplate.getForObject("https://www.baidu.com", String.class);
        System.out.println(quote);
        return "Hello World";
    }

}
