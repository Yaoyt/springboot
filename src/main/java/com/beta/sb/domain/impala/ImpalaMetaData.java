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

    public ImpalaMetaData(long id, int metaType, String metaData) {
        this.id = id;
        this.metaData = metaData;
        this.metaType = metaType;
    }

    public ImpalaMetaData() {
        this.id = 0;
        this.metaData = "";
        this.metaType = 0;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMetaData() {
        return metaData;
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
