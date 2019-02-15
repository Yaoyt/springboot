package com.beta.sb.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

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

    @Test
    public void test10(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2018-09-16 00:00:00", dtf);
        LocalDateTime localDateTime2 = localDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        System.out.println(localDateTime2);
    }

    @Test
    public void test2(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt1 = LocalDateTime.parse("2018-01-08 00:00:00", dtf);
        LocalDateTime ldt2 = LocalDateTime.parse("2018-01-03 00:00:00", dtf);
        System.out.println(ChronoUnit.DAYS.between(ldt1, ldt2));

    }

    @Test
    public void test6(){
        String fromDate = "2017-08-09 12:00:00";
        System.out.println(fromDate.substring(0, 4));
    }

    @Test
    public void test4(){
        Set<String> sets = Sets.newHashSet();
        sets.add("1");
        sets.add("1");
        sets.add("");
        sets.add(null);
        for (String str : sets) {
            System.out.println(str);
    }
    }

    @Test
    public void test5(){
        List<String> ll = new ArrayList<>();
        ll.add("a");
        ll.add("b");
        ll.add("c");
        List<String> lll = new ArrayList<>();
        lll.add("a");
        lll.add("b");
        lll.add("c");
        List<String> l2 =  ll.subList(1, 2);//[)
        List<String> l22 = lll.subList(1, 2);
        System.out.println(l2 == l22);
        l2.add("new");

        List<String> l3 = ll.subList(2, 3);
        System.out.println(ll);
        System.out.println(l2);
        System.out.println(l3);

    }



    @Test
    public void test3(){
        Integer rowIndex = 0;
        List<Integer> rowList = Lists.newArrayList();

    }
    @Test
    public void test7(){
        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime z1 = ZonedDateTime.parse("2019-01-20", dtf);
        System.out.println(z1);*/
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime z2 = ZonedDateTime.parse("2019-01-20 12:00:01", dtf2);
        System.out.println(z2);

    }

    @Test
    public void test8(){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 43; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        System.out.println(sb.toString());
    }

    @Test
    public void test9() throws Exception{
        String str1 = "20181022010203000";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(str1);
        System.out.println(sdf2.format(date));

    }
}
