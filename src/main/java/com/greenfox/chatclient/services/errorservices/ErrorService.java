package com.greenfox.chatclient.services.errorservices;

import com.greenfox.chatclient.models.ErrorMessage;

public interface ErrorService {

  ErrorMessage getNewErrorMessage(String message);

}
