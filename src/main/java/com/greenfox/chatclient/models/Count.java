package com.greenfox.chatclient.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Count {

  private Integer count;

  public Count(Integer count) {
    this.count = count;
  }

  public Count() {
  }
}
