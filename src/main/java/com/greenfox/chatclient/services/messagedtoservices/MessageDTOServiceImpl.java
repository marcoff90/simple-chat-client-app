package com.greenfox.chatclient.services.messagedtoservices;

import com.greenfox.chatclient.models.Message;
import com.greenfox.chatclient.models.MessageDTO;
import org.springframework.stereotype.Service;

@Service
public class MessageDTOServiceImpl implements MessageDTOService {

  @Override
  public MessageDTO getNewMessageDto(Message message) {
    return new MessageDTO(message);
  }
}
