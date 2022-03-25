package com.greenfox.chatclient.models;

import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Entity(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotNull
  private String login;
  @Nullable
  private String avatarUrl;
  private String password;
  private String username;
  private String apiKey;
  @OneToMany(mappedBy = "author")
  private List<Message> messages;

  public User() {

  }

  public User(String login, @Nullable String avatarUrl, String password) {
    this.login = login;
    this.avatarUrl = avatarUrl;
    this.password = password;
    this.username = login;
  }

  public User(String login, String password) {
    this(login, null, password);
    this.username = login;
  }

}
