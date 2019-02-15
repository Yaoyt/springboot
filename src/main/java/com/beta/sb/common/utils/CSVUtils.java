package com.beta.sb.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoyt on 17/6/21.
 *
 * @author yaoyt
 */
public class CSVUtils {
    /**
     * 导出
     *
     * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;

        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file,true);
            osw = new OutputStreamWriter(out,"GBK");
            bw =new BufferedWriter(osw,1024);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data);
                    bw.newLine();
                }
            }
            bw.flush();
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }

    /**
     * 导入
     *
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<String> importCsv(File file,int size){

        List<String> dataList=new ArrayList<String>();
        BufferedReader br=null;
        int countNum = 0;
        int countMax = size + 100;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"),10 * 1024 * 1024);
            String line = "";
            while ((line = br.readLine()) != null) {
                countNum ++;
                if(countNum >= countMax){
                    break;
                }
                if (countNum >= size && countNum < countMax) {
                    dataList.add(line);
                }

            }
        }catch (Exception e) {
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataList;
    }
}
