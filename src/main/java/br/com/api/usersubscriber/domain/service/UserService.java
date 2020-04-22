package br.com.api.usersubscriber.domain.service;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;

public interface UserService {
    User create(User user) throws InvalidRequestBodyException;
}
