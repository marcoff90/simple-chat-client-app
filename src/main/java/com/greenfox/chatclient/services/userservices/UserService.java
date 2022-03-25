package com.greenfox.chatclient.services.userservices;

import com.greenfox.chatclient.models.User;
import com.greenfox.chatclient.models.UserDTO;

public interface UserService {

  boolean addUser(User user);

  boolean isUserNameTaken(User user);

  boolean isUserInDatabase(User user);

  void setApiKey(String apiKey, User user);

  void resetApiKey(User user);

  User findUser(User user);

  void updateUser(String apiKey, User user);

  User findByApiKey(String apiKey);

  User findById(Integer id);

}
