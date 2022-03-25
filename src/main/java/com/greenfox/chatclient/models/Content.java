package com.greenfox.chatclient.models;

import lombok.Data;

@Data
public class Content {

  private String content;

  public Content() {
  }

  public Content(String content) {
    this.content = content;
  }
}
