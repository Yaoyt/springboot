package com.beta.sb.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * Created by yaoyt on 18/7/13.
 *
 * @author yaoyt
 */
public class MsgWebSocketClient extends WebSocketClient {

    public MsgWebSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("握手...");
        for(Iterator<String> it = serverHandshake.iterateHttpFields(); it.hasNext();) {
            String key = it.next();
            System.out.println(key+":"+serverHandshake.getFieldValue(key));
        }

    }

    @Override
    public void onMessage(String s) {
        System.out.println("接收到消息："+s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("关闭...");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("异常"+e);
    }
}
