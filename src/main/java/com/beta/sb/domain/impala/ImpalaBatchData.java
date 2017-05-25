package com.beta.sb.domain.impala;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 *类ImpalaBatchData的功能描述: 接收批量用户数据/商品数据/行为数据的封装类
 *@author yaoyt
 *@time 17/5/16 上午9:34
 */
public class ImpalaBatchData implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<ImpalaBaseData> datas;

    private Long cid;

    public List<ImpalaBaseData> getDatas() {
        return datas;
    }

    public void setDatas(List<ImpalaBaseData> batchData) {
        this.datas = batchData;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
