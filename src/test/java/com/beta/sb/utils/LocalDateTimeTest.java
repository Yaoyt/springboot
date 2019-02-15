package com.beta.sb.utils;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by yaoyt on 17/6/7.
 *
 * @author yaoyt
 */
public class LocalDateTimeTest {

    @Test
    public void test1() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("2017-05-07 12:00:00", dtf);
        DateTimeFormatter dft2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        String str2 = dft2.format(ldt);
        int a =Integer.valueOf(str2);
        System.out.println(a);
    }
}
