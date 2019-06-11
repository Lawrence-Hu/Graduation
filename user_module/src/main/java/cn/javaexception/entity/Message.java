package cn.javaexception.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Message {
    String fromUserId;
    String toUserId;
    String content;
    Boolean read = false;
    Long time;
}
