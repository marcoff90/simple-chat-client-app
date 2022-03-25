package com.greenfox.chatclient.services.apikeyservices;

import com.greenfox.chatclient.models.ApiKey;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

  @Override
  public ApiKey getNewApiKey() {
    return new ApiKey();
  }
}
