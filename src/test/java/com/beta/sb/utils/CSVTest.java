package com.beta.sb.utils;

import com.beta.sb.common.utils.CSVUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoyt on 17/6/21.
 *
 * @author yaoyt
 */
public class CSVTest {
    /**
     * CSV导出
     *
     * @throws Exception
     */
    @Test
    public void exportCsv() {
        List<String> dataList=new ArrayList<String>();
        dataList.add("1,张三,男");
        dataList.add("2,李四,男");
        dataList.add("3,小红,女");
        boolean isSuccess= CSVUtils.exportCsv(new File("/Users/yaoyt/Downloads/test_123.csv"), dataList);
        System.out.println(isSuccess);
    }

    /**
     * CSV导出
     *
     * @throws Exception
     */
    @Test
    public void importCsv()  {
        List<String> dataList = CSVUtils.importCsv(new File("/Users/yaoyt/Downloads/test2.csv"),1);

    }

    @Test
    public void importTest01() throws  Exception{
        CsvFileUtil u = new CsvFileUtil("/Users/yaoyt/Downloads/test2.csv" );
        u.readLine();
    }

}
