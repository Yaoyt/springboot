package com.beta.sb.thread.synchronizedTest;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by yaoyt on 19/2/27.
 * 对象逸出, 当一个对象还没有构造完时,就使它被其他线程可见.
 * @author yaoyt
 */
@Slf4j
public class EscapeTest {
    private int thisCanBeEscape = 10;
    public EscapeTest(){
        new InnerClass();
    }
    private class InnerClass {
        public InnerClass(){
            // this 指针逃逸
            log.info("{}", EscapeTest.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new EscapeTest();
    }

}
