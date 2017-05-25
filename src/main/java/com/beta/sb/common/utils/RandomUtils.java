package com.beta.sb.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Created by yaoyt on 17/5/24.
 *
 * @author yaoyt
 */
public class RandomUtils {
    public static String getRandomSex() { //length表示生成字符串的长度
        String base = "男女";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 1; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomAge() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100));
    }
    public static String getRandomStr(String... strs){
        Random random = new Random();
        int length = strs.length;
        int number = random.nextInt(length);
        return strs[number];

    }



    public static String getCurrentTime(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return time.format(dtf);
    }

    public static String getBoolean(){
        return String.valueOf(new Random().nextInt(2));
    }

    public static String getDouble(){
        double random = Math.random();
        int i = (int) (random * 10000) ;
        return String.valueOf( (double)i/100);
    }

    public static String getPrice(){
        double random = Math.random();
        int i = (int) (random * 1000000) ;
        return String.valueOf( (double)i/100);
    }

    public static String getRandomWeight() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10000));
    }
}
