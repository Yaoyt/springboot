package com.beta.sb.websocket;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * Created by yaoyt on 18/7/13.
 *
 * @author yaoyt
 */
public class VerifiSign2 {

    public static void main(String[] args) throws Exception {
        //读取keystore文件转换为keystore密钥库对象
        FileInputStream fis = new FileInputStream("/Users/yaoyt/workspace/space_intellij/springboot/jssecacerts");
        //FileInputStream fis = new FileInputStream("/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/security/cacerts");
        //因为生成证书的类型为JKS 也有其他的格式
        KeyStore keyStore = KeyStore.getInstance("JKS");
        //该密钥库的密码"888999"
        String storepass="changeit";
        String keypass = "changeit"; //别名密码
        keyStore.load(fis, storepass.toCharArray());
        fis.close();
        // 从keystore中读取证书和私钥
        //String alias = "wwwbkexcom";//别名
        String alias = "www.bkex.com-1";//别名
        Certificate certificate = keyStore.getCertificate(alias);
        //读取公钥对象
        PublicKey publicKey = certificate.getPublicKey();
        System.out.println("提取的公钥为:\n"+encodeBase64(publicKey.toString()));
        //读取私钥对象
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias,keypass.toCharArray());
        System.out.println("提取的私钥为:\n"+encodeBase64(privateKey.toString()));
    }

    public static String encodeBase64(String str) throws Exception {
        // 1.将传递进来的字符串密码 转换为字节数组 放到base64加密工具里 生产出一个加了密的字符串
        String base64Str = new BASE64Encoder().encode(str.getBytes("UTF-8"));
        return base64Str;
    }

    // 对密文字符串解密
    public static String decodeBase64(String base64Str) throws Exception {
        // 根据加了密的字符串 使用base64的解密工具里 获取原来的明文字符串密码
        byte[] bytes = new BASE64Decoder().decodeBuffer(base64Str);
        String generalStr = new String(bytes, "UTF-8");
        return generalStr;
    }

}
