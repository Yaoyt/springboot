package com.beta.sb.test;

import org.junit.Test;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

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
}
