package com.beta.sb.websocket;

import org.java_websocket.WebSocketImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by yaoyt on 18/7/13.
 *
 * @author yaoyt
 */
public class WsClientExample {
    public static void main( String[] args ) throws Exception {
        WebSocketImpl.DEBUG = true;

        WebSocketChatClient chatclient = new WebSocketChatClient( new URI( "ws://192.168.1.54:10010/websocket" ) );
        chatclient.connectBlocking();

        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
        while ( true ) {
            String line = reader.readLine();
            if( line.equals( "close" ) ) {
                chatclient.close();
            } else {
                chatclient.send( line );
            }
        }

    }
}
