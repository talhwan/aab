package com.thc.sprboot.socket;

import com.google.gson.Gson;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WsHandler extends TextWebSocketHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int list_size_limit_ws = 1000;

    private static List<WebSocketSession> list = new ArrayList<>();
    private static Map<String, String> map_session = new HashMap<>();

	@Builder
	public static class Message {
		private String wsType;
		private Object data;
	}
    public void sendByWsType(Message msg) throws Exception {
    	//logger.info("* msg : " + msg);
    	Gson gson = new Gson();
		TextMessage message = new TextMessage(gson.toJson(msg));
    	
    	for(WebSocketSession sess: list) {
        	String t_cate = map_session.get(sess.getId() + "");
        	if(t_cate.equals(msg.wsType)) {
        		sess.sendMessage(message);
        	}
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        logger.info("* payload : " + payload);
        for(WebSocketSession sess: list) {
        	sess.sendMessage(message);
        }
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	logger.info("1. 접속 : " + session);
    	if(list.size() < list_size_limit_ws) {
    		String session_id = session.getId();
        	String cate = session.getUri() + "";
        	
        	String t_param = "?cate=";
        	int int_param = cate.indexOf(t_param);
			cate = cate.substring(int_param);
			cate = cate.replace(t_param, "");
        	logger.info("cate : " + cate);

        	map_session.put(session_id, cate);
    		list.add(session);


    	} else {
    		Gson gson = new Gson();
        	/*TextMessage message = new TextMessage(gson.toJson(Message.builder().wsType("rejected").data(tbgaloneliveList).build()));
        	session.sendMessage(message);*/
    	}
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String session_id = session.getId() + "";
    	map_session.remove(session_id);
    	logger.info("2. 해제 : " + session);
        list.remove(session);
    }
}