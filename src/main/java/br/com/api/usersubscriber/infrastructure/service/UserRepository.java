package br.com.api.usersubscriber.infrastructure.service;

import br.com.api.usersubscriber.domain.entity.User;

public interface UserRepository {

    User create(User user);
}
