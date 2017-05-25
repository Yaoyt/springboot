package com.beta.sb.common.httpclient;

import com.alibaba.fastjson.JSON;
import com.common.sys.constants.UrlConstants;
import com.common.sys.exception.BusinessException;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yaoyt on 5/18/16.
 *
 * @author yaoyt
 */
public class HttpUtils {

    private static final Logger LOG = LogManager.getLogger(HttpUtils.class);

    private static PoolingHttpClientConnectionManager cm;
    private static HttpRequestRetryHandler httpRequestRetryHandler;
    private static CloseableHttpClient httpClient;
    private static RequestConfig requestConfig;

    static {

        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = MySSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();

        cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加到500
        cm.setMaxTotal(500);
        // 将每个路由基础的连接增加到100
        cm.setDefaultMaxPerRoute(200);
        // 将大数据目标主机的链接数增大到100
        HttpHost hpHost = new HttpHost(UrlConstants.BIG_DATA_HOST,UrlConstants.BIG_DATA_PORT);
        cm.setMaxPerRoute(new HttpRoute(hpHost), 100);

        //请求重试处理
        httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 5) {// 如果已经重试了5次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return true;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return true;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return true;
                }
                if (exception instanceof SSLException) {// ssl握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler)
                .build();

        // 配置请求的超时设置
        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000000)    //请求超时时间
                .setConnectTimeout(1000)              //连接超时时间
                .setSocketTimeout(10000000)               //
                .build();
    }
    /**
     *方法doGetUrl的功能描述:调用get请求
     *@author yaoyt
     *@time 6/1/16 6:00 PM
     */ 
    public static String doGetUrl(String url){
        Long startTime = System.currentTimeMillis();
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpget,HttpClientContext.create());
            HttpEntity responseEntity = response.getEntity();
            result = getResultContent(responseEntity);
            EntityUtils.consume(responseEntity);
            LOG.debug("调用接口{},状态:成功,返回:{},共消耗时间{}ms",url,result,System.currentTimeMillis() - startTime);
        } catch (IOException e) {
            LOG.error("调用接口{},状态:失败,共消耗时间{}ms",url,System.currentTimeMillis() - startTime,e);
            throw new BusinessException("httpGet调用URL失败",e);
        } finally {
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                throw new BusinessException("关闭HttpClient失败",e);
            }
        }
        return result;
    }

    /**
     *方法doPostUrl的功能描述:调用POST请求
     *@author yaoyt
     *@time 6/1/16 6:00 PM
     */ 
    public static String doPostUrl(String url, List<BasicNameValuePair> paras) {
        Long startTime = System.currentTimeMillis();
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        String result = "";

        try {
            post.setEntity(new UrlEncodedFormEntity(paras, "UTF-8"));
            response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            result = getResultContent(responseEntity);
            EntityUtils.consume(responseEntity);
            LOG.debug("调用接口{},参数{},状态:成功,返回:{},共消耗时间{}ms", url, JSON.toJSONString(paras), result,System.currentTimeMillis() - startTime);
        }catch (Exception e) {
            LOG.error("调用接口{},参数{},状态:失败,共消耗时间{}ms", url, JSON.toJSONString(paras), System.currentTimeMillis() - startTime,e);
            throw new BusinessException("httpPost调用URL失败",e);
        }finally {
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                throw new BusinessException("关闭HttpClient失败",e);
            }
        }
        return result;
    }

    /**
     *方法 doPostString 的功能描述: 传输无参 参数
     *@author yaoyt
     *@time 6/1/16 6:00 PM
     */
    public static String doPostString(String url, String value) {
        Long startTime = System.currentTimeMillis();
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            StringEntity requestEntity = new StringEntity(value, ContentType.create("text/plain", "UTF-8"));
            post.setEntity(requestEntity);
            response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            result = getResultContent(responseEntity);
            EntityUtils.consume(responseEntity);
            LOG.debug("调用接口{},参数{},状态:成功,返回:{},共消耗时间{}ms", url, value,result,System.currentTimeMillis() - startTime);
        }catch (Exception e) {
            LOG.error("调用接口{},参数{},状态:失败,共消耗时间{}ms", url, value, System.currentTimeMillis() - startTime,e);
            throw new BusinessException("httpPost调用URL失败",e);
        }finally {
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                throw new BusinessException("关闭HttpClient失败",e);
            }
        }
        return result;
    }

    public static String doPostImpala(String url,String authc, String para){
        Long startTime = System.currentTimeMillis();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("",authc );

        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        post.addHeader("Authorization", "Bearer ".concat(authc));

        CloseableHttpResponse response = null;
        String result = "";
        try {
            StringEntity requestEntity = new StringEntity(para, ContentType.create("application/json", "UTF-8"));
            post.setEntity(requestEntity);
            response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            result = getResultContent(responseEntity);
            EntityUtils.consume(responseEntity);
            LOG.debug("调用接口{},状态:成功,返回:{},共消耗时间{}ms", url,result,System.currentTimeMillis() - startTime);
        }catch (Exception e) {
            LOG.error("调用接口{},状态:失败,共消耗时间{}ms", url, System.currentTimeMillis() - startTime,e);
            throw new BusinessException("httpPost调用URL失败",e);
        }finally {
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                throw new BusinessException("关闭HttpClient失败",e);
            }
        }
        return result;
    }
    
    /**
     *方法doPostXMl的功能描述:以charset编码发送xml格式的数据
     *@author yaoyt
     *@time 6/7/16 9:41 AM
     */ 
    public static String doPostXMl(String url, String xmlContent,String charset) {
        Long startTime = System.currentTimeMillis();
        HttpPost post = new HttpPost(url);
        //默认以GBK的编码发送
        post.addHeader("Content-Type", "text/xml; charset=".concat(charset));
        StringEntity entity = new StringEntity(xmlContent, charset);
        CloseableHttpResponse response = null;
        String result = "";
        try {
            post.setEntity(entity);
            response = httpClient.execute(post);
            HttpEntity resEntity = response.getEntity();
            result = getResultContent(resEntity);
            EntityUtils.consume(resEntity);
            LOG.debug("调用接口{},参数{},状态:成功,返回:{},共消耗时间{}ms", url, xmlContent, result,System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            LOG.error("调用接口{},参数{},状态:失败,共消耗时间{}ms", url, xmlContent, System.currentTimeMillis() - startTime, e);
            throw new BusinessException("doPostXMl调用URL失败", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                throw new BusinessException("关闭HttpClient失败", e);
            }
        }
        return result;
    }

    /**
     *方法doPostXML的功能描述:调用post请求发送xml
     *@author yaoyt
     *@time 6/1/16 6:01 PM
     */
    public static String doPostXMl(String url, String xmlContent) {
       return doPostXMl(url,xmlContent,"GBK");
    }

    
    /**
     *方法getResultContent的功能描述:获取返回对象并转换为String输出
     *@author yaoyt
     *@time 6/1/16 6:01 PM
     */ 
    public static String getResultContent(HttpEntity entity) throws IOException {
        String result = "";
        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                result = EntityUtils.toString(entity, "utf-8");
            } finally {
                instream.close();
            }
        }
        return result;
    }



}
