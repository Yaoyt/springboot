package com.beta.sb.test;

import org.junit.Test;

/**
 * Created by yaoyt on 18/7/18.
 *
 * @author yaoyt
 */
public class StringTest {

    @Test
    public void test1(){
        String a = "120000";
        Integer position = 0;
        for (int i = a.length() -1; i >=0; i --) {
            char c = a.charAt(i);
            if ('0' != c) {
                position = i;
                break;
            }
        }
        System.out.println(position);
        System.out.println(a.substring(0, position+1));

    }

    @Test
    public void test2(){
        String a = "ACC&Accessories&Empty Case & Palette";
        int index = a.indexOf("&", 1);
        int index2 = a.indexOf("&", index + 1);
        System.out.println(index);
        System.out.println(index2);
        System.out.println(a.substring(0, index));
        System.out.println(a.substring(index+1,index2));
        System.out.println(a.substring(index2+1));
    }

    @Test
    public void test3(){
        for (int i = 0; i < 29; i ++) {
            int remain = i % 10;
            if (remain == 0) {
                System.out.println("top 10 total product" + (remain + 1));
            } else {
                System.out.println(" product" + (remain + 1));
            }
        }
    }

    @Test
    public void test4(){
        String s1 = "a123.123123";
        String[] a = s1.split("\\.");
        for (int i = 0; i < a.length; i ++) {
            System.out.println(a[i]);
        }
    }

    @Test
    public void test6(){
        String a = "u";
        System.out.println(a.indexOf("u"));

    }

    @Test
    public void test5(){
        String str = null;
        String str2 = str.trim();
        System.out.println(str2);

    }

    @Test
    public void test7(){
        String str = " 123123123 ";
        System.out.println(str+":"+str.length());
        String str2 = str.trim();
        System.out.println(str2+":"+str2.length());
    }

    @Test
    public void test8(){
        String str = "123123123123123";
        String[] strs = new String[]{str};
        System.out.println(strs.length);
    }


    public static void main(String[] args) {
        String str = "..1.123123";
        Integer index = str.lastIndexOf("a");
        System.out.println(index);
    }


}
