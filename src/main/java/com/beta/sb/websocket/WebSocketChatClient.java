package com.beta.sb.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yaoyt on 18/7/13.
 *
 * @author yaoyt
 */
public class WebSocketChatClient extends WebSocketClient{

    public WebSocketChatClient(URI serverUri ) {
        super( serverUri );
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        System.out.println( "Connected" );

    }

    @Override
    public void onMessage( String message ) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格
        System.out.println("时间: " + df.format(new Date())+ " got: " + message );

    }

    public void sendMessage(String message) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格
        System.out.println("时间: " + df.format(new Date())+ " send: " + message );
        this.send(message);
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        System.out.println(" 由于某种原因,段开了链接");
        System.out.println( "Disconnected" );
    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
    }
}
