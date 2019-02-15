package com.beta.sb.websocket;


import java.net.URISyntaxException;

/**
 * Created by yaoyt on 18/7/13.
 *
 * @author yaoyt
 */
public class WebClient {

    private static MsgWebSocketClient socketClient = null;


    public static void initClient(MsgWebSocketClient client) {
        socketClient = client;
        if( null != socketClient) {
            socketClient.connect();
            socketClient.send("2");
        }
        boolean flag = true;
        int i=1000;
        while(flag) {
            socketClient.send("2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(i == 0) {
                flag = false;
            }
        }
    }

    public static void main(String[] args) {
        try {
            WebClient.initClient(new MsgWebSocketClient("wss://ws.bkex.com/socket.io/?EIO=3&transport=websocket"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }


}
