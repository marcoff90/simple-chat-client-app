package com.greenfox.chatclient.services.messageservices;

import com.greenfox.chatclient.models.Content;
import com.greenfox.chatclient.models.Message;
import com.greenfox.chatclient.models.MessageDTO;
import com.greenfox.chatclient.models.Messages;
import com.greenfox.chatclient.repositories.MessageRepository;
import com.greenfox.chatclient.services.userservices.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  private MessageRepository messageRepository;
  private UserService userService;

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository, UserService userService) {
    this.messageRepository = messageRepository;
    this.userService = userService;
  }

  @Override
  public Message addMessage(Content content, String apiKey) {
    Message message = new Message(content);
    message.setAuthor(userService.findByApiKey(apiKey));
    saveMessage(message);
    return message;
  }

  @Override
  public Messages getMessages(Integer count) {
    List<MessageDTO> messages = new ArrayList<>();

    if (count == null) {
      return new Messages(messages); // returns empty list
    }

    try {
      messages = messageRepository.findAllSortByDateDesc().subList(0, count)
          .stream()
          .map(MessageDTO::new)
          .collect(Collectors.toList());
    } catch (Exception ignored) {
      messages = messageRepository.findAllSortByDateDesc()
          .stream()
          .map(MessageDTO::new)
          .collect(Collectors.toList());
    }
    return new Messages(messages);
  }

  @Override
  public void saveMessage(Message message) {
    messageRepository.save(message);
  }
}
