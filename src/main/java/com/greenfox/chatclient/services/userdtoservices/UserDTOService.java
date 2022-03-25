package com.greenfox.chatclient.services.userdtoservices;

import com.greenfox.chatclient.models.User;
import com.greenfox.chatclient.models.UserDTO;

public interface UserDTOService {

  UserDTO returnUserDTO(User user);

}
