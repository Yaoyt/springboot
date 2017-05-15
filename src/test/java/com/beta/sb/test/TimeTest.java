package com.beta.sb.test;

import org.junit.Test;

import java.net.InetAddress;
import java.util.Date;

/**
 * Created by yaoyt on 17/5/8.
 *
 * @author yaoyt
 */
public class TimeTest {

    @Test
    public void test1() throws  Exception{
        System.out.println(new Date().getTime());
        System.out.println(InetAddress.getLocalHost().getHostName().toString());
    }
}
