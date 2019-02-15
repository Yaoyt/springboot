package com.beta.sb.websocket;

import org.java_websocket.WebSocketImpl;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yaoyt on 18/7/13.
 *
 * @author yaoyt
 */
public class SSLClientExample {


   private static  WebSocketChatClient chatclient = null;

    public static void connect() {
        try {
            WebSocketImpl.DEBUG = false;
            chatclient = new WebSocketChatClient( new URI( "wss://ws.bkex.com/socket.io/?EIO=3&transport=websocket" ) );
            SSLContext sslContext = SSLContext.getInstance( "TLS" );
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] chain,String authType) throws CertificateException {
                }
            }};
            sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );
            SSLSocketFactory factory = sslContext.getSocketFactory();
            chatclient.setSocket( factory.createSocket() );
            chatclient.setConnectionLostTimeout(30);
            chatclient.connect();
            Thread.sleep(5000);
            chatclient.sendMessage("40/quotation");
            Thread.sleep(5000);
            chatclient.sendMessage("42/quotation,[\"qConnect\",{\"pair\":\"ETH_USDT\"}]");
        } catch (Exception e) {
            connect();
        }

    }

    public static void main( String[] args ) throws Exception {
        while (true) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格

            System.out.println("时间:" +df.format(new Date())+ " ,chatClient:" + (chatclient == null ? null : chatclient.getReadyState()));
            Thread.sleep(5000);
            if (chatclient == null || chatclient.isClosed()) {
                connect();
            }
        }


       /* BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
        while ( true ) {
            String line = reader.readLine();
            if( line.equals( "close" ) ) {
                chatclient.close();
            } else {
                chatclient.send( line );
            }
        }*/

    }
}
