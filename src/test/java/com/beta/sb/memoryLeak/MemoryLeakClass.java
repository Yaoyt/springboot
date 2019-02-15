package com.beta.sb.memoryLeak;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoyt on 18/12/6.
 *
 * @author yaoyt
 */
public class MemoryLeakClass {
    public static void main(String[] args) {
        makeOutOfMemory1();
    }

    /**
     * 造成内存溢出，这次重复添加一个Person对象到一个列表中，因为列表是强引用，所以无法被回收，
     * 从而最终导致内存溢出
     */
    public static void makeOutOfMemory1(){
        //无限往一个List中加对象，因为List是强引用，所以不会被GC，从而导致memory溢出
        List<Person> persons = new ArrayList<Person>();
        while( 1>0){
            persons.add( new Person("fakeperson","male",25));
        }
    }
}
