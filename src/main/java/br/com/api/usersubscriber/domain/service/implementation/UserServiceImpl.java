package br.com.api.usersubscriber.domain.service.implementation;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import br.com.api.usersubscriber.domain.service.UserService;
import br.com.api.usersubscriber.infrastructure.service.UserRepository;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) throws InvalidRequestBodyException {
        validateUserInputs(user);
        return userRepository.create(user);
    }

    private void validateUserInputs(User user) throws InvalidRequestBodyException {
        if (user.getName().trim().isEmpty()) {
            throw new InvalidRequestBodyException(
                    "The name field must not be empty.", HttpStatus.SC_UNPROCESSABLE_ENTITY);
        }
        if (user.getBirthDate().trim().isEmpty()) {
            throw new InvalidRequestBodyException(
                    "The birthDate field must not be empty.", HttpStatus.SC_UNPROCESSABLE_ENTITY);
        }
        if (user.getEmail().trim().isEmpty()) {
            throw new InvalidRequestBodyException(
                    "The email field must not be empty.", HttpStatus.SC_UNPROCESSABLE_ENTITY);
        }
    }
}
