package com.greenfox.chatclient.controllers;

import com.greenfox.chatclient.models.Content;
import com.greenfox.chatclient.models.Count;
import com.greenfox.chatclient.models.Message;
import com.greenfox.chatclient.services.errorservices.ErrorService;
import com.greenfox.chatclient.services.messagedtoservices.MessageDTOService;
import com.greenfox.chatclient.services.messageservices.MessageService;
import com.greenfox.chatclient.services.userservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

  private MessageService messageService;
  private MessageDTOService messageDTOService;
  private ErrorService errorService;
  private UserService userService;

  @Autowired
  public MessageController(MessageService messageService,
      MessageDTOService messageDTOService, ErrorService errorService, UserService userService) {
    this.messageService = messageService;
    this.messageDTOService = messageDTOService;
    this.errorService = errorService;
    this.userService = userService;
  }

  @PostMapping("/api/message/")
  public ResponseEntity<Object> storeMessage(@RequestHeader(required = false) String apiKey,
      @RequestBody(required = false) Content content) {
    // 401 unauthorized apiKey was not provided - error
    // 401 Provided ApiKey was invalid. - error
    // 500 internal server error wrong info, not complete info Please provide all information - just string
    // 200 returns messageDto
    if (apiKey == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorService.getNewErrorMessage("apiKey was not provided"));
    } else if (userService.findByApiKey(apiKey) == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorService.getNewErrorMessage("Provided ApiKey was invalid."));
    } else if (content.getContent() == null) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please provide all information");
    } else {
      try {
        Message toJson = messageService.addMessage(content, apiKey);
        return ResponseEntity.ok().body(messageDTOService.getNewMessageDto(toJson));
      } catch (Exception ignored) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please provide all information");
      }
    }
  }

  @PostMapping("/api/get-messages")
  public ResponseEntity<Object> showMessages(@RequestHeader(required = false) String apiKey,
      @RequestBody(required = false) Count count) {
    // 401 unauthorized apikey was not provided - error
    // 401 unauthorized provided apikey was invalid - error
    // 400 bad request when json isn't integer
    // 200 null json empty messages array
    // 200 array messages with message DTO

    if (apiKey == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorService.getNewErrorMessage("apiKey was not provided"));
    } else if (userService.findByApiKey(apiKey) == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorService.getNewErrorMessage("Provided ApiKey was invalid."));
    } else {
      return ResponseEntity.ok().body(messageService.getMessages(count.getCount()));
    }
  }
}
