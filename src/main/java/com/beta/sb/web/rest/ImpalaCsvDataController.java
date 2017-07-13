package com.beta.sb.web.rest;

import com.beta.sb.common.utils.CSVUtils;
import com.beta.sb.common.utils.ImpalaIdGenerator;
import com.beta.sb.common.utils.RandomUtils;
import com.beta.sb.domain.impala.ImpalaBaseData;
import com.beta.sb.domain.impala.ImpalaMetaData;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class ImpalaCsvDataController {

    private static Logger logger = LoggerFactory.getLogger(ImpalaInsertDataForTest.class);

    @Autowired
    private ImpalaIdGenerator impalaIdGenerator;

    @GetMapping("/insertDataToCsv")
    public String insertDataToCsv(@RequestParam(value="start",required=true) int start, @RequestParam(value="end",required=true)int end){
        for (int s = start; s < end; s ++) {
            logger.info("开始保存第{}万事件信息",s);
            List<ImpalaBaseData> datas = Lists.newArrayList();
            for(int i = 0; i < 10000; i ++) {
                ImpalaBaseData baseData = new ImpalaBaseData();
                List<ImpalaMetaData> baseAttrs = Lists.newArrayList();
                String name = RandomUtils.getRandomStr("张三","李四","王五","赵六","陈泊亿","陈泊贤","陈泊兮","陈泊夕","陈泊凡","陈泊尚","陈书坦","陈来君","陈寅坤","陈铖澄","陈绎暄","陈勇驰","陈榆廷","陈鹏麟","陈集双","陈隆年","陈阿伯","陈长老","陈镐民","陈重名","陈逢衡","陈通筹","陈贻范","陈诚任","陈被举","陈荣捷"
                );

                String email = RandomUtils.getStringRandom(new Random().nextInt(9) + 3) + RandomUtils.getRandomStr("@126.com","@qq.com","@163.com","@sina.com") ;
                int point = new Random().nextInt(100000);
                String phone = RandomUtils.getRandomStr("13","17","18","15") + RandomUtils.getIntRandom(9);
                String birthday = RandomUtils.getRandomBirthDay();
                String time1 = RandomUtils.getRandomEventTime();
                String time2 = RandomUtils.getRandomEventTime();
                String time3 = RandomUtils.getRandomEventTime();
                String time4 = RandomUtils.getRandomEventTime();
                String time11 = getIntDay(time1);
                String time44 = getIntDay(time4);
                String city = RandomUtils.getRandomCity();
                double price = Double.valueOf(RandomUtils.getPrice());
                ImpalaMetaData meta00 = new ImpalaMetaData(30L, 0, String.valueOf(impalaIdGenerator.getUniqueId()));
                // name
                ImpalaMetaData meta0 = new ImpalaMetaData(30L,0,name);
                // 邮箱
                ImpalaMetaData meta1 = new ImpalaMetaData(30L,0,email);
                // 积分
                ImpalaMetaData meta2 = new ImpalaMetaData(30L,1,String.valueOf(point));
                // 电话号码
                ImpalaMetaData meta3 = new ImpalaMetaData(30L,0,phone);
                // birthDay
                ImpalaMetaData meta4 = new ImpalaMetaData(30L,0,birthday);
                // 第一次下单时间
                ImpalaMetaData meta5 = new ImpalaMetaData(30L,2,time1);
                // 最近下单时间
                ImpalaMetaData meta6 = new ImpalaMetaData(30L,2,time2);
                ImpalaMetaData meta7 = new ImpalaMetaData(30L,2,time3);
                ImpalaMetaData meta8 = new ImpalaMetaData(30L,2,time4);
                // 城市
                ImpalaMetaData meta9 = new ImpalaMetaData(30L,0,city);
                // 金额
                ImpalaMetaData meta10 = new ImpalaMetaData(30L,3,String.valueOf(price));
                // 国家
                ImpalaMetaData meta11 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("中国","美国","英国","日本","韩国","印度","俄罗斯","发过"));
                // language
                ImpalaMetaData meta12 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("汉语","英语","日语","法语","韩语","俄语"));
                // 省份
                ImpalaMetaData meta13 = new ImpalaMetaData(30L,0, RandomUtils.getRandomProvince());
                // 操作系统
                ImpalaMetaData meta14 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("IOS","Android","MAC","WINDOWS"));
                // network type
                ImpalaMetaData meta15 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("WIFI","4G","3G","2G"));
                // user_status
                ImpalaMetaData meta16 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("激活","未激活"));
                // 性别
                ImpalaMetaData meta17 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("MALE","FEMALE"));
                // VIP
                ImpalaMetaData meta18 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("铜牌","银牌","金牌","钻石"));
                //第一次下单时间
                ImpalaMetaData meta19 = new ImpalaMetaData(30L,1,time11);
                //注册时间
                ImpalaMetaData meta20 = new ImpalaMetaData(30L,1,time44);

                baseAttrs.add(meta00);
                baseAttrs.add(meta0);
                baseAttrs.add(meta1);
                baseAttrs.add(meta2);
                baseAttrs.add(meta3);
                baseAttrs.add(meta4);
                baseAttrs.add(meta5);
                baseAttrs.add(meta6);
                baseAttrs.add(meta7);
                baseAttrs.add(meta8);
                baseAttrs.add(meta9);
                baseAttrs.add(meta10);
                baseAttrs.add(meta11);
                baseAttrs.add(meta12);
                baseAttrs.add(meta13);
                baseAttrs.add(meta14);
                baseAttrs.add(meta15);
                baseAttrs.add(meta16);
                baseAttrs.add(meta17);
                baseAttrs.add(meta18);
                baseAttrs.add(meta19);
                baseAttrs.add(meta20);
                baseData.setBaseAttrs(baseAttrs);
                datas.add(baseData);
            }
            List<String> strs = Lists.newArrayList();
            // 保存datas
            for (ImpalaBaseData baseData : datas) {
                List<ImpalaMetaData> baseAttrs = baseData.getBaseAttrs();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < baseAttrs.size(); i ++ ) {
                    ImpalaMetaData meta = baseAttrs.get(i);
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(meta.getMetaData());
                }
                strs.add(sb.toString());
            }
            boolean isSuccess= CSVUtils.exportCsv(new File("/Users/yaoyt/Downloads/test10W.csv"), strs);

            //System.out.println(JSON.toJSONString(strs));
            logger.info("保存成功: {} ", isSuccess);
        }
        return "success";
    }

    public static String getIntDay(String createTime) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sdf1.parse(createTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String str = sdf.format(d);
            return str;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
