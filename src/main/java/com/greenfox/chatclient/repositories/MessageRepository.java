package com.greenfox.chatclient.repositories;

import com.greenfox.chatclient.models.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

  @Query("SELECT m FROM messages m ORDER BY m.created DESC")
  List<Message> findAllSortByDateDesc();

}
