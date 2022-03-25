package com.greenfox.chatclient.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  private String username;
  private int userId;
  private String avatarUrl;

  public UserDTO(String username, int userId, String avatarUrl) {
    this.username = username;
    this.userId = userId;
    this.avatarUrl = avatarUrl;
  }

  public UserDTO(User user) {
    if (user.getUsername() == null) {
      this.username = user.getLogin();
    } else {
      this.username = user.getUsername();
    }
    this.userId = user.getId();
    this.avatarUrl = user.getAvatarUrl();
  }


}
