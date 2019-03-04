package com.beta.sb.guava;

import com.google.common.base.Strings;
import org.junit.Test;

/**
 * Created by yaoyt on 19/2/20.
 *
 * @author yaoyt
 */
public class StringUtil {

    @Test
    public void test1(){
        String input = "";
        boolean isNullOrEmpty = Strings.isNullOrEmpty(input);
        System.out.println("input " + (isNullOrEmpty?"is":"is not") + " null or empty.");
    }

}
