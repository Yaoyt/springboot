package com.beta.sb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by yaoyt on 17/5/5.
 *
 * @author yaoyt
 */
@Component
public class CommonProperties {

    @Value("${common.properties.name}")
    private String name;

    @Value("${common.properties.title}")
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
