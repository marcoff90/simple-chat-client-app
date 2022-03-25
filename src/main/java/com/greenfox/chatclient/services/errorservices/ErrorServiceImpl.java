package com.greenfox.chatclient.services.errorservices;

import com.greenfox.chatclient.models.ErrorMessage;
import org.springframework.stereotype.Service;

@Service
public class ErrorServiceImpl implements ErrorService {

  @Override
  public ErrorMessage getNewErrorMessage(String message) {
    return new ErrorMessage(message);
  }
}
