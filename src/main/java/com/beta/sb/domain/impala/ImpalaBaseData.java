package com.beta.sb.domain.impala;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 *类ImpalaBaseData的功能描述: 接收用户数据/商品数据/行为数据信息的封装类
 *@author yaoyt
 *@time 17/5/16 上午9:31
 */
public class ImpalaBaseData implements Serializable{

    private static final long serialVersionUID = 1L;

    private String usercode;

    private String itemcode;

    private long eventId;

    private long cid;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    private List<ImpalaMetaData> baseAttrs;

    public List<ImpalaMetaData> getBaseAttrs() {
        return baseAttrs;
    }

    public void setBaseAttrs(List<ImpalaMetaData> baseAttrs) {
        this.baseAttrs = baseAttrs;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
