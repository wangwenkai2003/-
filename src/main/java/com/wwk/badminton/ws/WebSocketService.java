package com.wwk.badminton.ws;

import com.alibaba.fastjson.JSONObject;
import com.wwk.badminton.pojo.Chat;
import com.wwk.badminton.pojo.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketService extends TextWebSocketHandler {
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 当建立 WebSocket 连接时调用，可以在这里处理一些初始化工作
        URI uri = session.getUri();
        String[] split = String.valueOf(uri).split("/");
        String  userId = split[split.length-1];

        if (userId != null) {
            sessions.put(userId, session);
            System.out.println("用户 " + userId + " 已连接");
        }
    }


    public void handleOnlineNotification(String userId) {
        List<Message> messages = getOfflineMessages(userId);
        if (messages != null && !messages.isEmpty()) {
            for (Message message : messages) {
                String fromUserId = message.getFrom();
                String toUserId = message.getTo();
                String content = message.getContent();

                JSONObject json = new JSONObject();
                json.put("from", fromUserId);
                json.put("to", toUserId);
                json.put("content", content);

                TextMessage textMessage = new TextMessage(json.toJSONString());
                WebSocketSession session = getSession(userId);
                if (session != null && session.isOpen()) {
                    try {
                        session.sendMessage(textMessage);
                    } catch (IOException ex) {
                        System.err.println("发送消息失败: " + ex.getMessage());
                    }
                }
            }
            // 删除已读的离线消息
            deleteOfflineMessages(userId);
        }
    }

    private List<Message> getOfflineMessages(String userId) {
        // 从数据库或消息队列中读取未读的离线消息
        // ...
        return null;
    }

    private void deleteOfflineMessages(String userId) {
        // 从数据库或消息队列中删除已读的离线消息
        // ...
    }

    public WebSocketSession getSession(String userId) {
        return sessions.get(userId);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = (String) message.getPayload();
        JSONObject json = JSONObject.parseObject(payload);
        String fromUserId = json.getString("from");
        String toUserId = json.getString("to");
        String content = json.getString("content");
        String ifImg = json.getString("ifImg");

        if ((fromUserId != null && toUserId != null)&&( content!=null || ifImg.equals("1"))) {
            System.out.println(ifImg);
            WebSocketSession toSession = sessions.get(toUserId);
            if (toSession != null && toSession.isOpen()) {
                toSession.sendMessage(new TextMessage(content));
                if (ifImg.equals("1")){
                    session.sendMessage(new TextMessage("对方在线且发的图片"));
                    return;
                }
                session.sendMessage(new TextMessage("对方在线"));
            } else {
                if (ifImg.equals("1")){
                    session.sendMessage(new TextMessage("对方不在线且发的图片"));
                    return;
                }
                session.sendMessage(new TextMessage("对方不在线"));
            }
        } else {
            session.sendMessage(new TextMessage("消息格式不正确"));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 处理传输错误
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 当 WebSocket 连接关闭时调用，可以在这里进行资源清理等工作
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}