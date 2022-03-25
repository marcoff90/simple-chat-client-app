package com.greenfox.chatclient.repositories;

import com.greenfox.chatclient.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByLogin(String login);

  User findByApiKey(String apiKey);

}
