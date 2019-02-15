package com.beta.sb.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
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
    public void test2(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime d1 = LocalDateTime.parse("2017-01-31 12:01:59", dtf);
        LocalDateTime d2 = LocalDateTime.parse("2017-01-31 12:02:00", dtf);



        LocalDateTime year1 = d1.withDayOfYear(1);
        LocalDateTime year2 = d2.withDayOfYear(1);
        System.out.println(ChronoUnit.YEARS.between(year1, year2));
        LocalDateTime m1 = d1.withDayOfMonth(1);
        LocalDateTime m2 = d2.withDayOfMonth(1);
        System.out.println(ChronoUnit.MONTHS.between(m1, m2));
        TemporalField temp = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDateTime w1 = d1.with(temp, 2);
        LocalDateTime w2 = d2.with(temp, 2);
        System.out.println(ChronoUnit.WEEKS.between(w1, w2));

        LocalDateTime h1 = d1.withMinute(0);
        LocalDateTime h2 = d2.withMinute(0);
        System.out.println(ChronoUnit.HOURS.between(h1, h2));

        LocalDateTime s1 = d1.withSecond(0);
        LocalDateTime s2 = d2.withSecond(0);
        System.out.println(ChronoUnit.MINUTES.between(s1, s2));



        //System.out.println(ChronoUnit.WEEKS.between(d1, d2));
        //long daysDiff = ChronoUnit.DAYS.between(d1, d2);
        //System.out.println(daysDiff);
        //System.out.println(ChronoUnit.WEEKS.between(d1.plusDays(-1), d2.plusDays(-1)));
        //
        //System.out.println(ChronoUnit.MINUTES.between(d1, d2));



    }

    @Test
    public void test3() throws ParseException {
        String str = "2010/6/25 :00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = sdf.parse(str);
        System.out.println(sdf.format(date));

    }

    /**
     * Example method for converting a byte to a String.
     */
    public void convertByteToString() {

        byte b = 65;

        //Using the static toString method of the Byte class
        System.out.println(Byte.toString(b));

        //Using simple concatenation with an empty String
        System.out.println(b + "");

        //Creating a byte array and passing it to the String constructor
        System.out.println(new String(new byte[] {b}));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TimeTest().convertByteToString();
    }
    @Test
    public void test11() throws  Exception{
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
    public void test12(){
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
    public void test13(){
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
