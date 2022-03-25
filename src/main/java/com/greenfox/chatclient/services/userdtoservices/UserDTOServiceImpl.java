package com.greenfox.chatclient.services.userdtoservices;

import com.greenfox.chatclient.models.User;
import com.greenfox.chatclient.models.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserDTOServiceImpl implements UserDTOService {

  @Override
  public UserDTO returnUserDTO(User user) {
    return new UserDTO(user);
  }
}
