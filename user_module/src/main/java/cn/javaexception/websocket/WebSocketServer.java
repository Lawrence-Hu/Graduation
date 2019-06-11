package cn.javaexception.websocket;

import cn.javaexception.entity.Message;
import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Component
public class WebSocketServer {
    private RedisTemplate<String,String> redisTemplate;

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String userId;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
        this.session=session;
        this.userId=userId;
        redisTemplate = (RedisTemplate<String,String>) applicationContext.getBean("redisTemplate");
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);

//        List<Message> messages = new ArrayList<>();
//        //获取所有消息
//        Set<String> keys = redisTemplate.keys(userId+"*");
//        if (keys==null){
//            return;
//        }
//        for (String key : keys) {
//            Message message = JSON.parseObject(redisTemplate.opsForValue().get(key), Message.class);
//            messages.add(message);
//        }
//        //根据不同用户进行分组
//        Map<String, List<Message>> collect = messages.stream().collect(Collectors.groupingBy(Message::getFromUserId));
//        Set<Map.Entry<String, List<Message>>> entries = collect.entrySet();
//        //分组后按时间排序消息
//        for (Map.Entry<String, List<Message>> entry : entries) {
//            List<Message> msg = entry.getValue()
//                    .stream()
//                    .filter(message -> !message.getRead()).sorted((o1, o2) -> o1.getTime() > o2.getTime() ? -1 : 1)
//                    .collect(Collectors.toList());
//            //排序后新的value
//            System.out.println(msg);
//            entry.setValue(msg);
//        }
 //       this.session.getAsyncRemote().sendText(JSON.toJSONString(entries));
        webSocketSet.add(this);     //加入set中
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
    }

    @OnMessage
    public void onMessage(String data, Session session) {
        //封装信息
//        Message message = JSON.parseObject(data,Message.class);
//        message.setTime(System.currentTimeMillis());
//        int flag = 0;
//        for (WebSocketServer server : webSocketSet) {
//            //如果当前用户在线
//            if (server.userId.equals(message.getToUserId())){
//               flag =1;
//               server.session.getAsyncRemote().sendText(data);
//               break;
//            }
//        }
//        //如果用户不在线 保存在redis中
//        if (flag ==0){
//            String key = message.getToUserId()+":"+System.currentTimeMillis();
//            redisTemplate.opsForValue().set(key,JSON.toJSON(message).toString());
//        }
    }
    public void update(){
        //用户的消息
        String userId = "xxxxx";
        //已查看来自XXX的消息
        String formUserId = "asdsakdj";
        //得到所有消息
        Set<String> keys = redisTemplate.keys(userId + "*");
        for (String key : keys) {
            Message message = JSON.parseObject(redisTemplate.opsForValue().get(key), Message.class);
            assert message != null;
            //将来自XXX的消息设置为已读
            if(message.getFromUserId().equals(formUserId)){
                message.setRead(true);
            }
            redisTemplate.opsForValue().set(key,JSON.toJSONString(message));
        }

    }

}
