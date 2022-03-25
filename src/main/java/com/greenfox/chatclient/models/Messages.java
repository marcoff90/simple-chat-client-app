package com.greenfox.chatclient.models;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Messages {

  private List<MessageDTO> messages;

  public Messages(List<MessageDTO> messages) {
    this.messages = messages;
  }

  public Messages() {
  }
}
