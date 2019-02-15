package com.beta.sb.test;

import com.alibaba.fastjson.JSON;
import com.beta.sb.domain.primary.User;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yaoyt on 18/8/14.
 *
 * @author yaoyt
 */
public class RegexTest {


    @Test
    public void test4(){
        User u1 = new User();
        u1.setAge(11);
        u1.setName("张三");
        u1.setId(1L);
        User u2 = new User();
        u2.setAge(12);
        u2.setId(2L);
        u2.setName("李四");
        List<User> users = Lists.newArrayList();
        users.add(u1);
        users.add(u2);

        String str = JSON.toJSONString(users);
        System.out.println(str);
    }

    @Test
    public void test3(){
        String sql = "select 1 from 123123;;;select 2 from 123123124;;;";
        String sql2 = "";
        String[] sql1List = sql.split(";;;");
        String[] sql2List = sql2.split(";;;");
        System.out.println(sql1List);
        System.out.println(sql2List);
    }

    @Test
    public void test1(){
        String sql = " select user_id from smg_user_2.user_id where col_17 between @@@week::0::first@@@ and @@@week::-1::last@@@ " +
                " and @@@year::-12::last@@@ and @@@day::10::first@@@ ";
        String sql2 = "@@@day::-1@@@ ++  @@@week::10@@@";
        String sql3 = "@@@day@@@";
        Pattern p = Pattern.compile("@@@(year|month|week|day)::(-)?\\d+::(first|last)@@@");
        Matcher m = p.matcher(sql);
        List<String> stringList = Lists.newArrayList();
        while(m.find()){
            String s = m.group();
            System.out.println(s);
            stringList.add(s);
        }

        for (int i = 0; i < stringList.size(); i ++) {
            String s = stringList.get(i);
            sql = sql.replaceAll(s, "abcdefg" + i);
        }
        System.out.println(sql);
    }

    @Test
    public void test2(){
        String param = "@@@week::-1::first@@@";
        param = param.replaceAll("@@@", "");
        String[] splitStrs = param.split("::");
        if (splitStrs.length != 3) {
            throw new RuntimeException("参数不正确");
        }
        String unit = splitStrs[0];
        Long num = Long.valueOf(splitStrs[1]);
        String type = splitStrs[2];
        // 获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();

        TemporalField weekTemporal = WeekFields.of(Locale.CHINA).dayOfWeek();

        if ("month".equals(unit)) {
            localDateTime = localDateTime.plusMonths(num);
            if ("first".equals(type)) {
                localDateTime = localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            } else {
                localDateTime = localDateTime.withDayOfMonth(1).plusMonths(1).minusDays(1).withHour(23).withMinute(59).withSecond(59);
            }

        } else if ("week".equals(unit)) {
            localDateTime = localDateTime.plusWeeks(num);
            if ("first".equals(type)) {
                localDateTime = localDateTime.with(weekTemporal,2).withHour(0).withMinute(0).withSecond(0).withNano(0);
            } else if ("last".equals(type)) {
                localDateTime = localDateTime.with(weekTemporal, 2).plusWeeks(1).minusDays(1).withHour(23).withMinute(59).withSecond(59);
            }
        } else if ("year".equals(unit)) {
            localDateTime = localDateTime.plusYears(num);
            if ("first".equals(type)) {
                localDateTime = localDateTime.withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            } else if ("last".equals(type)) {
                localDateTime = localDateTime.withDayOfYear(1).plusYears(1).minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            }
        }

        System.out.println(localDateTime);
    }

    @Test
    public void test5(){
        String number = "1";
        //String regExp = "^[1-9][0-9]*(\\.[0-9]{1,2})?$"; //n为小数位数
        String regExp = "^[1-9][0-9]*$"; //n为小数位数
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(number);
        System.out.println(m.matches());
    }

    @Test
    public void test6(){
        String number = "123中国abcv";
        String regExp = "^[\\u4e00-\\u9fa5a-zA-Z0-9]+$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(number);
        System.out.println(m.matches());
    }

    @Test
    public void test7(){
        String name = "8226767@qdum.com";
        String regExp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(name);
        System.out.println(m.matches());
    }
}


