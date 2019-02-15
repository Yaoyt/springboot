package com.beta.sb.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by yaoyt on 18/6/21.
 *
 * @author yaoyt
 */
public class BigDecimalTest {

    @Test
    public void test2(){
        Long l = 20180101123030000L;
        Long m = l / 100000000000L;
        System.out.println(m);
    }

    @Test
    public void test1(){
        BigDecimal decimal1 = BigDecimal.valueOf(0.00001);
        BigDecimal decimal2 = BigDecimal.valueOf(0.0000100001);
        BigDecimal decimal3 = BigDecimal.valueOf(0.00001);
        BigDecimal decimal4 = BigDecimal.valueOf(0.0000000001);


        System.out.println(decimal1.compareTo(decimal2));
        System.out.println(decimal1.compareTo(decimal3));

        System.out.println(decimal1.compareTo(decimal4));

        BigDecimal decimal5 = new BigDecimal("41852.5");
        java.util.Random r=new java.util.Random();

        for(int i=0;i<10;i++){
            Double d = Math.random() * 0.01 + 1;
            BigDecimal x = new BigDecimal(d.toString()).multiply(new BigDecimal("41852.5"));
            //System.out.println(x.setScale(4,BigDecimal.ROUND_FLOOR));
        }

        BigDecimal decimal6 = new BigDecimal("45600");
        BigDecimal decimal7 = new BigDecimal("41000");

        BigDecimal diff2 = decimal6.subtract(decimal7);
        BigDecimal haff = diff2.divide(new BigDecimal(5), 4, BigDecimal.ROUND_FLOOR);
        Double d = Math.random();
        BigDecimal randomBigDecimal = new BigDecimal(d.toString()).multiply(haff);
        BigDecimal result = decimal6.subtract(randomBigDecimal);
        BigDecimal result2 = result.setScale(4, BigDecimal.ROUND_FLOOR);
        System.out.println(result2);

    }

    public BigDecimal caculateSellPrice(BigDecimal sellOnePrice, BigDecimal buyOnePrice) {
        BigDecimal diff = sellOnePrice.subtract(buyOnePrice);
        BigDecimal haff = diff.divide(new BigDecimal(2));
        Double d = Math.random() * 0.01 + 1;
        BigDecimal randomBigDecimal = new BigDecimal(d.toString()).multiply(haff);
        BigDecimal result = buyOnePrice.add(randomBigDecimal);
        return result.setScale(4, BigDecimal.ROUND_FLOOR);
    }
    @Test
    public void test3(){
        BigDecimal sellOnePrice = new BigDecimal("41000");
        BigDecimal buyOnePrice = new BigDecimal("39000");

        BigDecimal diff = sellOnePrice.subtract(buyOnePrice);
        BigDecimal haff = diff.divide(new BigDecimal("2"));
        Double d = Math.random() * 0.5  + 1;
        BigDecimal randomBigDecimal = new BigDecimal(d.toString()).multiply(haff);
        BigDecimal result = buyOnePrice.add(randomBigDecimal);
        System.out.println(result.setScale(4, BigDecimal.ROUND_FLOOR));
    }

    @Test
    public void test4(){
        BigDecimal b1 = new BigDecimal("2.323131");
        BigDecimal b2 = new BigDecimal("200");
        BigDecimal b3 = b1.divide(b2).setScale(6, BigDecimal.ROUND_FLOOR).multiply(new BigDecimal("100"));
        System.out.println(b3);

    }

    @Test
    public void test5(){
        BigDecimal b1 = new BigDecimal("847.578");
        b1 = b1.setScale(1, BigDecimal.ROUND_HALF_UP);
        System.out.println(b1.toString());
    }

    @Test
    public void test8(){
        BigDecimal b1 = new BigDecimal("12.12312312310");
        BigDecimal b2 = new BigDecimal("12.1231231231");
        System.out.println(b1.compareTo(b2));
        BigDecimal b3 = new BigDecimal("23522");
        System.out.println(b3.divide(new BigDecimal("1000"), 1, BigDecimal.ROUND_FLOOR));

    }
}
