package br.com.api.usersubscriber.infrastructure.service;

import br.com.api.usersubscriber.domain.entity.User;

import java.util.List;

public interface UserRepository {
    User create(User user);
    List<User> getAllUsers();
}
