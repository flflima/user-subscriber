package br.com.api.usersubscriber.infrastructure.service.implementation;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.infrastructure.service.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User create(User user) {
        System.out.println("User created");
        return user;
    }
}
