package com.beta.sb.config;

import com.beta.sb.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yaoyt on 17/5/5.
 *
 * @author yaoyt
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PropertiesTest {
    @Autowired
    private CommonProperties commonProperties;

    @Test
    public void testProperties(){
        System.out.println(commonProperties.getName());
        System.out.println(commonProperties.getTitle());
    }
}
