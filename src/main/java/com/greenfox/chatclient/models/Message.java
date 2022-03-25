package com.greenfox.chatclient.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "messages")
@Getter
@Setter
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String content;
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  @ManyToOne()
  private User author;

  public Message() {
    this.created = new Date();
  }

  public Message(Content content) {
    this.content = content.getContent();
    this.created = new Date();
  }
}
