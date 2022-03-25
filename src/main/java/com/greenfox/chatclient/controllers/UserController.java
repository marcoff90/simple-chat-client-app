package com.greenfox.chatclient.controllers;

import com.greenfox.chatclient.models.ApiKey;
import com.greenfox.chatclient.models.User;
import com.greenfox.chatclient.services.apikeyservices.ApiKeyService;
import com.greenfox.chatclient.services.errorservices.ErrorService;
import com.greenfox.chatclient.services.userdtoservices.UserDTOService;
import com.greenfox.chatclient.services.userservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/user")
public class UserController {

  private UserService userService;
  private UserDTOService userDTOService;
  private ApiKeyService apiKeyService;
  private ErrorService errorService;

  @Autowired
  public UserController(UserService userService, UserDTOService userDTOService,
      ApiKeyService apiKeyService, ErrorService errorService) {
    this.userService = userService;
    this.userDTOService = userDTOService;
    this.apiKeyService = apiKeyService;
    this.errorService = errorService;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> storeUser(@RequestBody(required = false) User user) {

    // 400 bad request empty object
    // 409 conflict login already taken
    // 500 internal server error different type object -> please provide all information!
    // 200 ok -> returns userDTO

    if (user == null) {
      return ResponseEntity.badRequest().build();
    } else {

      if (userService.isUserNameTaken(user)) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Login already taken");
      } else {
        if (userService.addUser(user)) {
          return ResponseEntity.ok().body(userDTOService.returnUserDTO(user));
        } else {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please provide all information!");
        }
      }
    }
  }

  @PostMapping("/login")
  public ResponseEntity<Object> loginUser(@RequestBody(required = false) User user) {

    // 400 missing login / password - the login/password is required
    // 200 ok - returns apikey

    if (user == null) {
      return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
    } else {
      if (user.getLogin() == null) {
        return ResponseEntity.badRequest().body("The login field is required");
      } else if (user.getPassword() == null) {
        return ResponseEntity.badRequest().body("The password field is required");
      } else {
        if (userService.isUserInDatabase(user)) {
          ApiKey apiKey = apiKeyService.getNewApiKey(); // generates new apikey and sets it to user
          userService.setApiKey(apiKey.getApiKey(), user);
          return ResponseEntity.ok().body(apiKey);
        } else {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Provided login data was incorrect");
        }
      }
    }
  }

  @PostMapping("/update")
  public ResponseEntity<Object> updateUser(@RequestBody(required = false) User user, @RequestHeader(required = false) String apiKey) {

    // 401 no api key apiKey was not provided
    // 200 returns userDTO
    // * React will send the apiKey from user to header

    if (apiKey == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorService.getNewErrorMessage("apiKey was not provided."));
    } else if (userService.findByApiKey(apiKey) == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorService.getNewErrorMessage("Provided ApiKey was invalid"));
    } else {
      userService.updateUser(apiKey, user);
      return ResponseEntity.ok().body(userDTOService.returnUserDTO(userService.findByApiKey(apiKey)));
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<Object> logoutUser(@RequestHeader(required = false) String apiKey) {
    // 401 no apikey
    // 401 invalid apikey
    // 200 boolean true
    if (apiKey == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorService.getNewErrorMessage("apiKey was not provided."));
    } else if (userService.findByApiKey(apiKey) == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorService.getNewErrorMessage("Provided ApiKey was invalid"));
    } else {
      userService.resetApiKey(userService.findByApiKey(apiKey));
      return ResponseEntity.ok().body(true);
    }
  }
}

