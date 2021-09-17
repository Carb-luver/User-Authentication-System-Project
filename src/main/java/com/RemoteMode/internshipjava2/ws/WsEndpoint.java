package com.RemoteMode.internshipjava2.ws;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WsEndpoint extends TextWebSocketHandler {

    //private Object TextMessage;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        TextMessage textMessage = new TextMessage("Connected!");
    }

}
