package com.beta.sb.datetime;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Created by yaoyt on 18/7/2.
 *
 * @author yaoyt
 */
public class DateTimeTest {

    /**
     *方法的功能描述:
     *  获取当前日期所在周的第一天,即周一
     *@author yaoyt
     *@time 18/7/2 下午1:57
     */
    @Test
    public void test1(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2018-06-29 01:02:03", df);
        TemporalField weekTemporal = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDateTime beginWeek = localDateTime.with(weekTemporal, 2).withHour(0).withMinute(0).withSecond(0);
        System.out.println("当前周第一天为:" + df.format(beginWeek));
    }

    /**
     *方法的功能描述:
     *  获取当前周的最后一天
     *@author yaoyt
     *@time 18/7/2 下午3:33
     */
    @Test
    public void test11(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2018-06-29 01:02:03", df);
        TemporalField weekTemporal = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDateTime beginWeek = localDateTime.with(weekTemporal, 7).withHour(0).withMinute(0).withSecond(0);
        beginWeek = beginWeek.plusDays(1);
        System.out.println("当前周第一天为:" + df.format(beginWeek));
    }

    /**
     *方法的功能描述:
     *  获取当前月的第一天
     *@author yaoyt
     *@time 18/7/2 下午2:03
     */
    @Test
    public void test2(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2018-06-29 01:02:03", df);
        LocalDateTime date2 = localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0); // 必须将原时间对象重新赋值,原时间不会发生变化
        System.out.println("当前月第一天为:" + df.format(date2));
    }

    /**
     *方法的功能描述:
     *  获取当前年的第一天
     *@author yaoyt
     *@time 18/7/2 下午2:06
     */
    @Test
    public void test3(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2018-06-29 01:02:03", df);
        LocalDateTime date2 = localDateTime.withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0); // 必须将原时间对象重新赋值,原时间不会发生变化
        System.out.println("当前年第一天为:" + df.format(date2));

    }
}
