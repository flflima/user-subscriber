package br.com.api.usersubscriber.infrastructure.service.implementation;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.infrastructure.service.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private List<User> users = new ArrayList<>();

    @Override
    public User create(User user) {
        System.out.println("User created");
        this.users.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        System.out.println("Gets all user");
        return users;
    }
}
