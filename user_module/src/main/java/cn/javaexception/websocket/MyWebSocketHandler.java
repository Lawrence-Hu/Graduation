package cn.javaexception.websocket;

import cn.javaexception.entity.Message;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MyWebSocketHandler implements WebSocketHandler {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    private static Hashtable<String, WebSocketSession> hashTable = new Hashtable<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = session.getUri().getPath().substring(session.getUri().getPath().lastIndexOf("/") + 1);
        System.out.println(userId);
        List<Message> messages = new ArrayList<>();
        //获取所有消息
        Set<String> keys = redisTemplate.keys(userId+"*");
        if (keys==null){
            return;
        }
        for (String key : keys) {
            Message message = JSON.parseObject(redisTemplate.opsForValue().get(key), Message.class);
            messages.add(message);
        }
        //根据不同用户进行分组
        Map<String, List<Message>> collect = messages.stream().collect(Collectors.groupingBy(Message::getFromUserId));
        Set<Map.Entry<String, List<Message>>> entries = collect.entrySet();
        //分组后按时间排序消息
        for (Map.Entry<String, List<Message>> entry : entries) {
            List<Message> msg = entry.getValue()
                    .stream()
                    .filter(message -> !message.getRead()).sorted((o1, o2) -> o1.getTime() > o2.getTime() ? -1 : 1)
                    .collect(Collectors.toList());
            //排序后新的value
            System.out.println(msg);
            entry.setValue(msg);
        }
        session.sendMessage(new TextMessage(JSON.toJSONString(entries)));
        hashTable.put(userId,session);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        Object data = webSocketMessage.getPayload();
        Message message = JSON.parseObject(data.toString(),Message.class);
        message.setTime(System.currentTimeMillis());
        Set<Map.Entry<String, WebSocketSession>> entries = hashTable.entrySet();
        for (Map.Entry<String, WebSocketSession> entry : entries) {
            if (entry.getKey().equals(message.getToUserId())){
                entry.getValue().sendMessage(new TextMessage(JSON.toJSONString(message)));
            }
        }
        String key = message.getToUserId()+":"+System.currentTimeMillis();
        redisTemplate.opsForValue().set(key,JSON.toJSON(message).toString());
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("离开了");
        hashTable.remove(this);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
