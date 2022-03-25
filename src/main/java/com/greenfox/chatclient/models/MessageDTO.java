package com.greenfox.chatclient.models;

import java.util.Date;
import lombok.Data;

@Data
public class MessageDTO {

  private int id;
  private String content;
  private Date created;
  private UserDTO author;

  public MessageDTO() {
  }

  public MessageDTO(Message message) {
    this.id = message.getId();
    this.content = message.getContent();
    this.created = message.getCreated();
    this.author = new UserDTO(message.getAuthor());
  }

}
