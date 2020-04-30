package br.com.api.usersubscriber.domain.service.implementation;

import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.model.Movie;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import br.com.api.usersubscriber.domain.service.UserService;
import br.com.api.usersubscriber.infrastructure.gateway.MovieGateway;
import br.com.api.usersubscriber.infrastructure.repository.UserRepository;
import lombok.Data;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  private MovieGateway movieGateway;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, MovieGateway movieGateway) {
    this.userRepository = userRepository;
    this.movieGateway = movieGateway;
  }

  @Override
  public User create(User user) throws InvalidRequestBodyException {
    validateUserInputs(user);
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUsers() {
    movieGateway.getRandomMovie();
    return userRepository.findAll();
  }

  private void validateUserInputs(User user) throws InvalidRequestBodyException {
    if (user.getName() == null || user.getName().trim().isEmpty()) {
      throw new InvalidRequestBodyException(
          "The name field must not be null or empty.", HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }
    if ( user.getBirthDate().trim().isEmpty()) {
      throw new InvalidRequestBodyException(
          "The birthDate field must not be null or empty.", HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }
    if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
      throw new InvalidRequestBodyException(
          "The email field must not be null or empty.", HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }
  }

  // TODO method that retrieves a user list and send to a queue
  public boolean notifyUsers() {
    final List<User> notifiableUsers = userRepository.findByNotifyMeTrue();

    System.out.println(notifiableUsers.size());
    return true;
  }


  public User updateUserMovie(final User user) {
    final Movie movie = movieGateway.getRandomMovie();
    final User updateUser = new User(user, movie);
    return userRepository.save(updateUser);
  }
}

