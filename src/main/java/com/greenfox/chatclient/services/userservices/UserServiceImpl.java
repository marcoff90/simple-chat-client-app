package com.greenfox.chatclient.services.userservices;

import com.greenfox.chatclient.models.User;
import com.greenfox.chatclient.repositories.UserRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean addUser(User user) {
    if (user.getLogin() == null) {
      return false;
    } else {
      if (!isUserNameTaken(user)) {
        this.userRepository.save(user);
      }
      return true;
    }
  }

  @Override
  public boolean isUserNameTaken(User user) {
    return findUser(user) != null;
  }

  @Override
  public boolean isUserInDatabase(User user) {
    return Objects.equals(findUser(user).getUsername(), user.getUsername()) && Objects.equals(findUser(user).getPassword(), user.getPassword());
  }

  @Override
  public void setApiKey(String apiKey, User user) {
    User toUpdate = findUser(user);
    toUpdate.setApiKey(apiKey);
    userRepository.save(toUpdate);
  }

  @Override
  public void resetApiKey(User user) {
    user.setApiKey(null);
    userRepository.save(user);
  }

  @Override
  public User findUser(User user) {
    return userRepository.findByLogin(user.getLogin());
  }

  @Override
  public void updateUser(String apiKey, User user) {

    if (user.getUsername() != null) {
      userRepository.findByApiKey(apiKey).setUsername(user.getUsername());
    }
    if (user.getAvatarUrl() != null) {
      userRepository.findByApiKey(apiKey).setAvatarUrl(user.getAvatarUrl());
    }
    userRepository.save(userRepository.findByApiKey(apiKey));
  }

  @Override
  public User findByApiKey(String apiKey) {
    return userRepository.findByApiKey(apiKey);
  }

  @Override
  public User findById(Integer id) {
    return userRepository.findById(id).orElse(null);
  }

}
