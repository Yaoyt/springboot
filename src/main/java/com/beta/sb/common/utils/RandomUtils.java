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
        int i = new Random().nextInt(10000);
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

    public static String getRandomBirthDay(){
        String year = getRandomStr("1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011");
        String month = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12");
        String day = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28");
        StringBuffer sb = new StringBuffer();
        sb.append(year).append(month).append(day);
        return sb.toString();
    }

    public static String getRandomEventTime(){
        //String month = getRandomStr("03","04","05");
        String month = getRandomStr("05");
        String day = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28");
        String hour = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        String mi = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19",
                "20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39",
                "40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
        String ss = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19",
                "20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39",
                "40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");

        StringBuffer sb = new StringBuffer();
        sb.append("2017-").append(month).append("-").append(day).append(" ").append(hour).append(":").append(mi).append(":").append(ss);
        return sb.toString();
    }

    public static String getRandomEventTime(String... months ){
        //String month = getRandomStr("03","04","05");
        String month = getRandomStr(months);
        String day = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28");
        String hour = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        String mi = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19",
                "20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39",
                "40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");
        String ss = getRandomStr("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19",
                "20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39",
                "40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");

        StringBuffer sb = new StringBuffer();
        sb.append("2017-").append(month).append("-").append(day).append(" ").append(hour).append(":").append(mi).append(":").append(ss);
        return sb.toString();
    }

    public static String getRandomProvince(){
        return getRandomStr("北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省", "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区", "新疆维吾尔自治区", "香港特别行政区", "澳门特别行政区");
        //return getRandomStr("BEIJING", "TIANJIN", "SHANGHAI", "CHONGQING", "HEBEI", "SHANXI", "LIAONING", "JINLIN", "HEILONGJIANG", "JIANGSU", "ZHEJIANG", "ANHUI", "FUJIAN", "JIANGXI", "SHANDONG", "HENAN", "HUBEI", "HUNAN", "GUANGDONG", "HAINAN", "SICHUAN", "GUIZHOU", "YUNNAN", "SHANXI", "GANSU", "QINGHAI", "TAIWAN", "NEIMENGU", "GUANGXI", "XIZANG", "NINGXIA", "XINJIANG", "XIANGGANG", "AOMEN");
    }

    //生成随机数字和字母,
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    public static String getIntRandom(int length){
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    public static String getRandomCity(){
        return getRandomStr("济南市","青岛市","临沂市","济宁市","菏泽市","烟台市","淄博市","泰安市","潍坊市","日照市","威海市","滨州市","东营市","聊城市","德州市","莱芜市","枣庄市");
    }

}
