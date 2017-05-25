package com.beta.sb.domain.impala;

import com.alibaba.fastjson.JSON;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *类ImpalaMetaData的功能描述: Impala 接收属性 的 封装类
 *@author yaoyt
 *@time 17/5/16 上午9:29
 */
public class ImpalaMetaData {
    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    private long id;

    private int metaType;

    private String metaData;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMetaData() {
        return metaData;
    }

    public Object getJavaMetaData(){
        Object o = new Object();
        switch (metaType){
            case 0:
                o = String.valueOf(metaData);
                break;
            case 1:
                o = Long.valueOf(metaData);
                break;
            case 2:
                o = String.valueOf(metaData);
                break;
            case 3:
                o = Double.valueOf(metaData);
                break;
        }
        return o;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public int getMetaType() {
        return metaType;
    }

    public void setMetaType(int metaType) {
        this.metaType = metaType;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
