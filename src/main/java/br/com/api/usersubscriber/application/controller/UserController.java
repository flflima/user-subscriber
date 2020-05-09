package br.com.api.usersubscriber.application.controller;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import br.com.api.usersubscriber.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Api(value = "user", description = "Operates actions on an user")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ApiOperation(value = "Creates an user", response = User.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "User created with success"),
        @ApiResponse(code = 404, message = "The resource was not found"),
        @ApiResponse(code = 500, message = "An internal error occurred")
      })
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<User> create(@RequestBody User user) throws InvalidRequestBodyException {
    User createdUser = this.userService.create(user);
    final ResponseEntity<User> responseEntity =
        new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    return responseEntity;
  }

  @ApiOperation(value = "Gets all users", response = User.class)
  @ApiResponses(
          value = {
                  @ApiResponse(code = 200, message = "Returns all users"),
                  @ApiResponse(code = 404, message = "The resource was not found"),
                  @ApiResponse(code = 500, message = "An internal error occurred")
          })
  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
  }

  @ApiOperation(value = "Alerts all users", response = User.class)
  @ApiResponses(
          value = {
                  @ApiResponse(code = 202, message = "Alert request was accepted"),
                  @ApiResponse(code = 404, message = "The resource was not found"),
                  @ApiResponse(code = 500, message = "An internal error occurred")
          })
  @RequestMapping(value = "/alert",method = RequestMethod.POST, produces = "application/json")
  @ResponseStatus(code = HttpStatus.ACCEPTED)
  public ResponseEntity alertAllUsers() throws Exception {
    if (!this.userService.notifyUsers()) {
      // TODO
      throw new Exception("");
    }
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @ApiOperation(value = "Alerts an user by ID", response = User.class)
  @ApiResponses(
          value = {
                  @ApiResponse(code = 202, message = "Alert request was accepted"),
                  @ApiResponse(code = 404, message = "The resource was not found"),
                  @ApiResponse(code = 500, message = "An internal error occurred")
          })
  @RequestMapping(value = "{id}/alert",method = RequestMethod.POST, produces = "application/json")
  @ResponseStatus(code = HttpStatus.ACCEPTED)
  public ResponseEntity alertUser(@PathVariable(value = "id") String userId) throws Exception {
    if (!this.userService.notifyUserById(userId)) {
      // TODO
      throw new Exception("");
    }
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
