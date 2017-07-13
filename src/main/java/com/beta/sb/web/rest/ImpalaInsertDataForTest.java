package com.beta.sb.web.rest;

import com.alibaba.fastjson.JSON;
import com.beta.sb.common.httpclient.HttpUtils;
import com.beta.sb.common.utils.CSVUtils;
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

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by yaoyt on 17/5/24.
 *
 * @author yaoyt
 */
@RestController
@RequestMapping("/impala/v2")
public class ImpalaInsertDataForTest {

    private static String url = "http://localhost:8081/api/v2/";

    private static String auth = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTQ5OTIzMzQxN30.uzjomX3JJsh8mGT5whyX6n1dcW9Pa2Jn1EnHogambbJKKqge8Z9AzDQv0_6kFtPpj969-M8tk4Lf2hwKnvu-WQ";

    private static Logger logger = LoggerFactory.getLogger(ImpalaInsertDataForTest.class);

    /**
     *方法的功能描述:插入用户数据,userNum 代表 插入的用户数量,单位是500
     *@author yaoyt
     *@time 17/5/24 下午1:40
     */
    @GetMapping("/insertuserinfo")
    public String insert(@RequestParam(value="start",required=true) int start, @RequestParam(value="end",required=true)int end) {
        ImpalaBatchData batch = new ImpalaBatchData();
        batch.setCid(118L);
        for (int j = start ; j < end; j ++) {
            logger.info("正在保存第 {}*100 条数据",j);
            List<ImpalaBaseData> datas = Lists.newArrayList();
            for(int i = 0; i < 100; i ++) {
                //int userId = j * 100000 + i;
                int userId = new Random().nextInt(10);
                ImpalaBaseData baseData = new ImpalaBaseData();
                baseData.setUsercode("user_code_" + userId);
                List<ImpalaMetaData> baseAttrs = Lists.newArrayList();
                // metaType : 0-字符串,1-整数,2-时间字符串,3-小数,4-boolean, 5-tinyint
                // 姓名
                ImpalaMetaData meta0 = new ImpalaMetaData(10L,0, RandomUtils.getRandomStr("张三","李四","王五","赵六"));
                baseAttrs.add(meta0);
                //年龄
                ImpalaMetaData meta1 = new ImpalaMetaData(11L,1, RandomUtils.getRandomAge());
                baseAttrs.add(meta1);
                //注册时间
                ImpalaMetaData meta2 = new ImpalaMetaData(12L,2, RandomUtils.getRandomEventTime());
                baseAttrs.add(meta2);
                //体重
                ImpalaMetaData meta3 = new ImpalaMetaData(13L,3, RandomUtils.getDouble());
                baseAttrs.add(meta3);
                //是否是会员
                ImpalaMetaData meta4 = new ImpalaMetaData(14L,4, RandomUtils.getRandomStr("true","false"));
                baseAttrs.add(meta4);
                //是否已绑定手机号
                ImpalaMetaData meta5 = new ImpalaMetaData(15L,5, RandomUtils.getRandomStr("true","false"));
                baseAttrs.add(meta5);
                //性别
                ImpalaMetaData meta6 = new ImpalaMetaData(16L,0, RandomUtils.getRandomSex());
                baseAttrs.add(meta6);
                //城市
                ImpalaMetaData meta7 = new ImpalaMetaData(17L,0, RandomUtils.getRandomStr("北京","上海","广东","深圳"));
                baseAttrs.add(meta7);
                //民族
                ImpalaMetaData meta8 = new ImpalaMetaData(18L,0, RandomUtils.getRandomStr("汉族","朝鲜族","壮族","回族"));
                baseAttrs.add(meta8);
                //身份证号
                ImpalaMetaData meta9 = new ImpalaMetaData(19L,0, RandomUtils.getRandomStr("371325199001"+userId));
                baseAttrs.add(meta9);

                baseData.setBaseAttrs(baseAttrs);

                datas.add(baseData);
            }
            batch.setDatas(datas);
            System.out.println(JSON.toJSONString(datas));
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
    public String insertItemInfo(@RequestParam(value="start",required=true) int start, @RequestParam(value="end",required=true)int end) {
        ImpalaBatchData batch = new ImpalaBatchData();
        batch.setCid(118L);
        for (int j = start ; j < end; j ++) {
            logger.info("正在保存第 {}*100 条数据",j);
            List<ImpalaBaseData> datas = Lists.newArrayList();
            for(int i = 0; i < 100; i ++) {
                int codeId = new Random().nextInt(10);
                ImpalaBaseData baseData = new ImpalaBaseData();
                baseData.setItemcode("item_code_" + codeId);
                List<ImpalaMetaData> baseAttrs = Lists.newArrayList();

                // metaType : 0-字符串,1-整数,2-时间字符串,3-小数,4-boolean, 5-tinyint
                // 商品名称
                ImpalaMetaData meta0 = new ImpalaMetaData(20L,0, RandomUtils.getRandomStr("小米","苹果","OPPO","三星NOTE"));
                baseAttrs.add(meta0);
                // 商品重量
                ImpalaMetaData meta1 = new ImpalaMetaData(21L,1, RandomUtils.getRandomWeight());
                baseAttrs.add(meta1);
                // 生产时间
                ImpalaMetaData meta2 = new ImpalaMetaData(22L,2, RandomUtils.getRandomEventTime());
                baseAttrs.add(meta2);
                // 商品价格
                ImpalaMetaData meta3 = new ImpalaMetaData(23L,3, RandomUtils.getPrice());
                baseAttrs.add(meta3);

                //是否提供保修
                ImpalaMetaData meta4 = new ImpalaMetaData(24L,4, RandomUtils.getRandomStr("true","false"));
                baseAttrs.add(meta4);

                //是否7天无理由退换
                ImpalaMetaData meta5 = new ImpalaMetaData(25L,5, RandomUtils.getRandomStr("true","false"));
                baseAttrs.add(meta5);

                //商品颜色
                ImpalaMetaData meta6 = new ImpalaMetaData(26L,0, RandomUtils.getRandomStr("红色","黄色","黑色"));
                baseAttrs.add(meta6);

                //生产地址
                ImpalaMetaData meta7 = new ImpalaMetaData(27L,0, RandomUtils.getRandomStr("青岛","潍坊","济南","临沂"));
                baseAttrs.add(meta7);
                //报装风格
                ImpalaMetaData meta8 = new ImpalaMetaData(28L,0, RandomUtils.getRandomStr("简约","礼盒"));
                baseAttrs.add(meta8);
                //子型号
                ImpalaMetaData meta9 = new ImpalaMetaData(29L,0, RandomUtils.getRandomStr("6","7","8","9"));
                baseAttrs.add(meta9);

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
    public String insertEventInfo(@RequestParam(value="start",required=true) int start, @RequestParam(value="end",required=true)int end){
        ImpalaBatchData batch = new ImpalaBatchData();
        batch.setCid(118L);
        for (int s = start; s < end; s ++) {
            logger.info("开始请求第{}万事件信息",s);
            List<ImpalaBaseData> datas = Lists.newArrayList();
            for(int i = 0; i < 20000; i ++) {
                ImpalaBaseData baseData = new ImpalaBaseData();
                baseData.setUsercode("user_code_" + new Random().nextInt(10));
                baseData.setItemcode("item_code_" + new Random().nextInt(10));
                String eventTime = RandomUtils.getRandomEventTime();
                baseData.setCreateTime(eventTime);
                baseData.setCreateDate(eventTime.substring(0,10));
                baseData.setEventId(3);
                List<ImpalaMetaData> baseAttrs = Lists.newArrayList();
                // 购买时间
                ImpalaMetaData meta0 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("国美","苏宁","微店","天猫","其他"));
                baseAttrs.add(meta0);
                // 屏幕宽度
                ImpalaMetaData meta1 = new ImpalaMetaData(31L,1, RandomUtils.getRandomWeight());
                baseAttrs.add(meta1);
                // 加入购物车的时间
                ImpalaMetaData meta2 = new ImpalaMetaData(32L,2, RandomUtils.getRandomEventTime());
                baseAttrs.add(meta2);
                // 支付的金额
                ImpalaMetaData meta3 = new ImpalaMetaData(33L,3, RandomUtils.getPrice());
                baseAttrs.add(meta3);

                // 是否有折扣
                ImpalaMetaData meta4 = new ImpalaMetaData(34L,4, RandomUtils.getRandomStr("true","false"));
                baseAttrs.add(meta4);

                // 是否使用了优惠券
                ImpalaMetaData meta5 = new ImpalaMetaData(35L,5, RandomUtils.getRandomStr("true","false"));
                baseAttrs.add(meta5);

                // 操作系统版本
                ImpalaMetaData meta6 = new ImpalaMetaData(36L,0, RandomUtils.getRandomStr("IOS","Android","Windows","MAC"));
                baseAttrs.add(meta6);

                // 商品所在地
                ImpalaMetaData meta7 = new ImpalaMetaData(37L,0, RandomUtils.getRandomStr("青岛","潍坊","济南","临沂"));
                baseAttrs.add(meta7);

                // 使用的浏览器版本
                ImpalaMetaData meta8 = new ImpalaMetaData(38L,0, RandomUtils.getRandomStr("简约","礼盒"));
                baseAttrs.add(meta8);

                //子型号
                ImpalaMetaData meta9 = new ImpalaMetaData(39L,0, RandomUtils.getRandomStr("6","7","8","9"));
                baseAttrs.add(meta9);

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


    /**
     *方法的功能描述:插入用户数据,userNum 代表 插入的用户数量,单位是500
     *@author yaoyt
     *@time 17/5/24 下午1:40
     */
    @GetMapping("/inserteventForTest")
    public String inserteventinfoNoPartition(@RequestParam(value="start",required=true) int start, @RequestParam(value="end",required=true)int end){
        ImpalaBatchData batch = new ImpalaBatchData();
        batch.setCid(111L);
        for (int s = start; s < end; s ++) {
            logger.info("开始请求第{}万事件信息",s);
            List<ImpalaBaseData> datas = Lists.newArrayList();
            for(int i = 0; i < 1000; i ++) {
                ImpalaBaseData baseData = new ImpalaBaseData();
                baseData.setUsercode("user_code_" + new Random().nextInt(100000));
                baseData.setItemcode("item_code_" + new Random().nextInt(100000));
                String eventTime = RandomUtils.getRandomEventTime();
                baseData.setCreateTime(eventTime);
                baseData.setCreateDate(eventTime.substring(0,10));
                baseData.setEventId(1);
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
                String time22 = getIntDay(time2);
                String time33 = getIntDay(time3);
                String time44 = getIntDay(time4);
                String city = RandomUtils.getRandomCity();
                double price = Double.valueOf(RandomUtils.getPrice());

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
                ImpalaMetaData meta16 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("ACTIVE","INACTIVE"));
                // 性别
                ImpalaMetaData meta17 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("MALE","FEMALE"));
                // VIP
                ImpalaMetaData meta18 = new ImpalaMetaData(30L,0, RandomUtils.getRandomStr("QINGTONG","BAIYIN","HUANGJIN","ZUANSHI"));
                //第一次下单时间
                ImpalaMetaData meta19 = new ImpalaMetaData(30L,1,time11);
                //注册时间
                ImpalaMetaData meta20 = new ImpalaMetaData(30L,1,time44);





                /*ImpalaMetaData meta17 = new ImpalaMetaData(30L,0,RandomUtils.getRandomProvince());
                //手机号码前三位
                ImpalaMetaData meta19 = new ImpalaMetaData(30L,0,phone.substring(0,3));
                // name 的长度
                ImpalaMetaData meta20 = new ImpalaMetaData(30L,1,String.valueOf(name.length()));
                // 邮箱的长度
                ImpalaMetaData meta21 = new ImpalaMetaData(30L,1,String.valueOf(email.length()));
                // 城市的长度
                ImpalaMetaData meta22 = new ImpalaMetaData(30L,1,String.valueOf(city.length()));
                // 积分的范围
                ImpalaMetaData meta23 = new ImpalaMetaData(30L,1,String.valueOf(((int)point/1000) * 1000));
                // 生日的分区
                ImpalaMetaData meta24 = new ImpalaMetaData(30L,1,birthday);
                // 4个时间
                ImpalaMetaData meta26 = new ImpalaMetaData(30L,1,time22);
                ImpalaMetaData meta27 = new ImpalaMetaData(30L,1,time33);
                // 消费金额
                ImpalaMetaData meta29 = new ImpalaMetaData(30L,1,String.valueOf(((int)price/1000) * 1000));*/


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
                /*baseAttrs.add(meta21);
                baseAttrs.add(meta22);
                baseAttrs.add(meta23);
                baseAttrs.add(meta24);
                baseAttrs.add(meta25);
                baseAttrs.add(meta26);
                baseAttrs.add(meta27);
                baseAttrs.add(meta28);
                baseAttrs.add(meta29);*/
                baseData.setBaseAttrs(baseAttrs);

                datas.add(baseData);

            }
            batch.setDatas(datas);
            //System.out.println(JSON.toJSONString(batch));
            HttpUtils.doPostImpala(url+"batch/inserteventForTest",auth, JSON.toJSONString(batch));
            logger.info("保存成功");
        }
        return "success";
    }

    /**
     *方法的功能描述:插入用户数据,userNum 代表 插入的用户数量,单位是500
     *@author yaoyt
     *@time 17/5/24 下午1:40
     */
    @GetMapping("/importCsv")
    public String importCsv(@RequestParam(value="start",required=true) int start, @RequestParam(value="end",required=true)int end){
        ImpalaBatchData batch = new ImpalaBatchData();
        batch.setCid(120L);
        for (int s = start; s < end; s ++) {
            int countStart = s * 100000;
            Long startTime = System.currentTimeMillis();

            List<String> dataList = CSVUtils.importCsv(new File("/Users/yaoyt/Downloads/test3.csv"),countStart);
            //List<String> dataList = CSVUtils.importCsv(new File("/root/test.csv"),countStart);
            List<ImpalaBaseData> datas = Lists.newArrayList();

            for (int i = 0; i < dataList.size(); i ++) {
                String data = dataList.get(i);
                String[] attrs = data.split(",");
                ImpalaBaseData baseData = new ImpalaBaseData();
                baseData.setUsercode(attrs[0]);
                List<ImpalaMetaData> baseAttrs = Lists.newArrayList();
                for (int j = 0; j < attrs.length; j ++) {
                    String attr = attrs[j];
                    /*if("激活".equals(attr)){
                        attr = "激活";
                    } else if ("未激活" .equals(attr)) {
                        attr = "未激活";
                    }*/
                    ImpalaMetaData meta;
                    /*if (j == 0 || j == 3 ||j == 20 || j ==21 ) {
                        meta = new ImpalaMetaData(30L, 1, attr);
                    }else if (j == 11) {
                        meta = new ImpalaMetaData(30L, 3, attr);
                    }else{
                        meta = new ImpalaMetaData(30L, 0, attr);
                    }*/
                    if ( j == 3 ||j == 20 || j ==21 ) {
                        meta = new ImpalaMetaData(10L + j, 1, attr);
                    }else if ( j == 6 || j == 7 || j == 8 || j == 9  ){
                        meta = new ImpalaMetaData(10L + j, 2, attr);
                    }else if (j == 11) {
                        meta = new ImpalaMetaData(10L + j, 3, attr);
                    }else{
                        meta = new ImpalaMetaData(10L + j, 0, attr);
                    }
                    baseAttrs.add(meta);
                }
                baseData.setBaseAttrs(baseAttrs);
                datas.add(baseData);
            }
            batch.setDatas(datas);
            //System.out.println(JSON.toJSONString(batch));
            logger.info("开始请求第{}千事件信息,数据整理时间:{}ms",s,System.currentTimeMillis() - startTime);
            HttpUtils.doPostImpala(url+"batch/insertuser",auth, JSON.toJSONString(batch));
            logger.info("保存成功");
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
