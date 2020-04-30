package br.com.api.usersubscriber.application.controller;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import br.com.api.usersubscriber.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> create(@RequestBody User user) throws InvalidRequestBodyException {
    User createdUser = this.userService.create(user);
    final ResponseEntity<User> responseEntity =
        new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    return responseEntity;
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
  }

  @PostMapping(value = "/alert")
  public ResponseEntity alertAllUsers() {
    this.userService.notifyUsers();
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
