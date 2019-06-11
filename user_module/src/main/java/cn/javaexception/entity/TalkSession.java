package cn.javaexception.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.websocket.Session;

@Data
@Accessors(chain = true)
public class TalkSession {
  private Session session;
  private String userId;
}
