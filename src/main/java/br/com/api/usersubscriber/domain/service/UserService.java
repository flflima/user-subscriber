package br.com.api.usersubscriber.domain.service;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface UserService {
  User create(User user) throws InvalidRequestBodyException;

  List<User> getAllUsers();

  boolean notifyUsers() throws JsonProcessingException;

  User updateUserMovie(User user);
}
