package com.greenfox.chatclient.services.messagedtoservices;

import com.greenfox.chatclient.models.Message;
import com.greenfox.chatclient.models.MessageDTO;

public interface MessageDTOService {

  MessageDTO getNewMessageDto(Message message);

}
