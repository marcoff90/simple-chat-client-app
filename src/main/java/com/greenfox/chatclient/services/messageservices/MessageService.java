package com.greenfox.chatclient.services.messageservices;

import com.greenfox.chatclient.models.Content;
import com.greenfox.chatclient.models.Message;
import com.greenfox.chatclient.models.MessageDTO;
import com.greenfox.chatclient.models.Messages;
import java.util.List;

public interface MessageService {

  Message addMessage(Content content, String apiKey);

  Messages getMessages(Integer count);

  void saveMessage(Message message);


}
