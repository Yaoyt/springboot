package com.beta.sb.websocket;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by yaoyt on 18/7/13.
 *
 * @author yaoyt
 */
public class WebSocketClient {

    public static void main(String[] args) {
        //System.setProperty("javax.net.ssl.trustStore", "/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/security/jssecacerts");
        System.out.println(System.getProperty("javax.net.ssl.trustStore"));
        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("wss://ws.bkex.com/socket.io/?EIO=3&transport=websocket"));

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                @Override
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            clientEndPoint.sendMessage("{'EIO':'3','transport':'websocket'}");

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
