package com.beta.sb.web.rest;

import com.alibaba.fastjson.JSON;
import com.beta.sb.common.httpclient.HttpUtils;
import com.beta.sb.common.utils.RandomUtils;
import com.beta.sb.domain.impala.ImpalaBaseData;
import com.beta.sb.domain.impala.ImpalaBatchData;
import com.beta.sb.domain.impala.ImpalaMetaData;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * Created by yaoyt on 17/5/24.
 *
 * @author yaoyt
 */
@RestController
@RequestMapping("/impala")
public class ImpalaInsertController {

    private static String url = "http://localhost:8081/api/";

    private static String auth = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTQ5NzQxOTE4MX0.8-JddTsrbgeCCuftbbFsq5z315HzEZssvA-hGp4rnGdsxWaKV3wTT75ltQKxb2Znuo6MeGdli7WTk14OVY0KoQ";

    private static Logger logger = LoggerFactory.getLogger(ImpalaInsertController.class);

    /**
     *方法的功能描述:插入用户数据,userNum 代表 插入的用户数量,单位是500
     *@author yaoyt
     *@time 17/5/24 下午1:40
     */
    @GetMapping("/insertuserinfo")
    public String insert(@RequestParam(value="num",required=true) long num) {
        ImpalaBatchData batch = new ImpalaBatchData();
        batch.setCid(111L);
        for (int j = 0; j < num; j ++) {
            logger.info("正在保存第 {}*100 条数据",j);
            List<ImpalaBaseData> datas = Lists.newArrayList();
            for(int i = 0; i < 100; i ++) {
                int userId = new Random().nextInt(10000000);
                ImpalaBaseData baseData = new ImpalaBaseData();
                baseData.setUsercode("user_code_" + userId);
                List<ImpalaMetaData> baseAttrs = Lists.newArrayList();
                //设置姓名属性,metaType : 0-字符串,1-整数,2-时间字符串,3-小数
                ImpalaMetaData meta1 = new ImpalaMetaData();
                meta1.setId(11L);
                meta1.setMetaData("张三"+userId);
                meta1.setMetaType(0);
                baseAttrs.add(meta1);
                //性别
                ImpalaMetaData meta2 = new ImpalaMetaData();
                meta2.setId(12L);
                meta2.setMetaData(RandomUtils.getRandomSex());
                meta2.setMetaType(0);
                baseAttrs.add(meta2);
                //年龄
                ImpalaMetaData meta3 = new ImpalaMetaData();
                meta3.setId(13L);
                meta3.setMetaData(RandomUtils.getRandomAge());
                meta3.setMetaType(1);
                baseAttrs.add(meta3);
                //城市
                ImpalaMetaData meta4 = new ImpalaMetaData();
                meta4.setId(14L);
                meta4.setMetaData(RandomUtils.getRandomStr("北京","上海","广东","深圳"));
                meta4.setMetaType(0);
                baseAttrs.add(meta4);
                //民族
                ImpalaMetaData meta5 = new ImpalaMetaData();
                meta5.setId(15L);
                meta5.setMetaData(RandomUtils.getRandomStr("汉族","朝鲜族","壮族","回族"));
                meta5.setMetaType(0);
                baseAttrs.add(meta5);
                //身份证号
                ImpalaMetaData meta6 = new ImpalaMetaData();
                meta6.setId(16L);
                meta6.setMetaData("371325199001"+userId);
                meta6.setMetaType(0);
                baseAttrs.add(meta6);
                //出生年月
                ImpalaMetaData meta7 = new ImpalaMetaData();
                meta7.setId(17L);
                meta7.setMetaData("19900123");
                meta7.setMetaType(0);
                baseAttrs.add(meta7);
                //注册时间
                ImpalaMetaData meta8 = new ImpalaMetaData();
                meta8.setId(18L);
                meta8.setMetaData(RandomUtils.getCurrentTime());
                meta8.setMetaType(2);
                baseAttrs.add(meta8);
                //是否为会员
                ImpalaMetaData meta9 = new ImpalaMetaData();
                meta9.setId(19L);
                meta9.setMetaData(RandomUtils.getBoolean());
                meta9.setMetaType(1);
                baseAttrs.add(meta9);
                //体重
                ImpalaMetaData meta10 = new ImpalaMetaData();
                meta10.setId(20L);
                meta10.setMetaData(RandomUtils.getDouble());
                meta10.setMetaType(3);
                baseAttrs.add(meta10);

                baseData.setBaseAttrs(baseAttrs);

                datas.add(baseData);
            }
            batch.setDatas(datas);
            String result = HttpUtils.doPostImpala(url+"batch/insertuser",auth,JSON.toJSONString(batch));
            logger.info("保存成功");
        }
        return "success";
    }

    /**
     *方法的功能描述:请求插入商品信息,num 的单位是 100
     *@author yaoyt
     *@time 17/5/24 下午1:48
     */
    @GetMapping("/insertiteminfo")
    public String insertItemInfo(@RequestParam(value="num",required=true) long num) {
        ImpalaBatchData batch = new ImpalaBatchData();
        batch.setCid(111L);
        for (int j = 0; j < num; j ++) {
            logger.info("正在保存第 {}*100 条数据",j);
            List<ImpalaBaseData> datas = Lists.newArrayList();
            for(int i = 0; i < 100; i ++) {
                int codeId = new Random().nextInt(10000000);
                ImpalaBaseData baseData = new ImpalaBaseData();
                baseData.setItemcode("item_code_" + codeId);
                List<ImpalaMetaData> baseAttrs = Lists.newArrayList();
                //设置商品名称属性,metaType : 0-字符串,1-整数,2-时间字符串,3-小数
                ImpalaMetaData meta1 = new ImpalaMetaData();
                meta1.setId(21L);
                meta1.setMetaData("小米"+codeId);
                meta1.setMetaType(0);
                baseAttrs.add(meta1);
                //商品价格
                ImpalaMetaData meta2 = new ImpalaMetaData();
                meta2.setId(22L);
                meta2.setMetaData(RandomUtils.getPrice());
                meta2.setMetaType(3);
                baseAttrs.add(meta2);
                //商品重量
                ImpalaMetaData meta3 = new ImpalaMetaData();
                meta3.setId(23L);
                meta3.setMetaData(RandomUtils.getRandomWeight());
                meta3.setMetaType(1);
                baseAttrs.add(meta3);
                //商品颜色
                ImpalaMetaData meta4 = new ImpalaMetaData();
                meta4.setId(24L);
                meta4.setMetaData(RandomUtils.getRandomStr("红色","黄色","黑色"));
                meta4.setMetaType(0);
                baseAttrs.add(meta4);
                //生产地址
                ImpalaMetaData meta5 = new ImpalaMetaData();
                meta5.setId(25L);
                meta5.setMetaData(RandomUtils.getRandomStr("青岛","潍坊","济南","临沂"));
                meta5.setMetaType(0);
                baseAttrs.add(meta5);
                //生产时间
                ImpalaMetaData meta6 = new ImpalaMetaData();
                meta6.setId(26L);
                meta6.setMetaData(RandomUtils.getCurrentTime());
                meta6.setMetaType(2);
                baseAttrs.add(meta6);
                //保质期
                ImpalaMetaData meta7 = new ImpalaMetaData();
                meta7.setId(27);
                meta7.setMetaData(RandomUtils.getRandomStr("6","12","18"));
                meta7.setMetaType(1);
                baseAttrs.add(meta7);
                //子型号
                ImpalaMetaData meta8 = new ImpalaMetaData();
                meta8.setId(28);
                meta8.setMetaData(RandomUtils.getRandomStr("L","XL","S"));
                meta8.setMetaType(0);
                baseAttrs.add(meta8);
                //风格
                ImpalaMetaData meta9 = new ImpalaMetaData();
                meta9.setId(29);
                meta9.setMetaData(RandomUtils.getRandomStr("欧美","韩系","日系"));
                meta9.setMetaType(0);
                baseAttrs.add(meta9);
                //包装
                ImpalaMetaData meta10 = new ImpalaMetaData();
                meta10.setId(30);
                meta10.setMetaData(RandomUtils.getRandomStr("简约","礼盒"));
                meta10.setMetaType(0);
                baseAttrs.add(meta10);

                baseData.setBaseAttrs(baseAttrs);

                datas.add(baseData);

            }
            batch.setDatas(datas);
            HttpUtils.doPostImpala(url+"batch/insertitem",auth,JSON.toJSONString(batch));
        }
        logger.info("保存成功");
        return "success";
    }




    /**
     *方法的功能描述:插入用户数据,userNum 代表 插入的用户数量,单位是500
     *@author yaoyt
     *@time 17/5/24 下午1:40
     */
    @GetMapping("/inserteventinfo")
    public String insertEventInfo(@RequestParam(value="num",required=true) long num){
        ImpalaBatchData batch = new ImpalaBatchData();
        batch.setCid(111L);
        for (int s = 0; s < num; s ++) {
            logger.info("开始请求第{}万事件信息",s);
            List<ImpalaBaseData> datas = Lists.newArrayList();
            for(int i = 0; i < 10000; i ++) {
                ImpalaBaseData baseData = new ImpalaBaseData();
                baseData.setUsercode("user_code_" + new Random().nextInt(10000000));
                baseData.setItemcode("item_code_" + new Random().nextInt(100000));
                baseData.setEventId(2);
                List<ImpalaMetaData> baseAttrs = Lists.newArrayList();
                //event_id = 1, 购买时间
                ImpalaMetaData meta1 = new ImpalaMetaData();
                meta1.setId(31L);
                meta1.setMetaData(RandomUtils.getCurrentTime());
                meta1.setMetaType(2);
                baseAttrs.add(meta1);
                //event_id = 1,支付金额
                ImpalaMetaData meta2 = new ImpalaMetaData();
                meta2.setId(32L);
                meta2.setMetaData(RandomUtils.getPrice());
                meta2.setMetaType(3);
                baseAttrs.add(meta2);
                //event_id = 1, 付款渠道
                ImpalaMetaData meta3 = new ImpalaMetaData();
                meta3.setId(33L);
                meta3.setMetaData(RandomUtils.getRandomStr("支付宝","微信","银联","POS机"));
                meta3.setMetaType(0);
                baseAttrs.add(meta3);
                //event_id = 1, 购买商场
                ImpalaMetaData meta4 = new ImpalaMetaData();
                meta4.setId(34L);
                meta4.setMetaData(RandomUtils.getRandomStr("国美","苏宁","微店","天猫","其他"));
                meta4.setMetaType(0);
                baseAttrs.add(meta4);
                //event_id = 1, 购买数量
                ImpalaMetaData meta5 = new ImpalaMetaData();
                meta5.setId(35L);
                meta5.setMetaData(RandomUtils.getRandomStr("1","2","3"));
                meta5.setMetaType(1);
                baseAttrs.add(meta5);

                baseData.setBaseAttrs(baseAttrs);

                datas.add(baseData);

            }
            batch.setDatas(datas);
            //System.out.println(JSON.toJSONString(batch));
            HttpUtils.doPostImpala(url+"batch/insertevent",auth, JSON.toJSONString(batch));
            logger.info("保存成功");
        }
        return "success";
    }
}
