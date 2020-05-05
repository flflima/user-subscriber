package br.com.api.usersubscriber.domain.service.implementation;

import br.com.api.usersubscriber.UserSubscriberApplication;
import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.model.Movie;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import br.com.api.usersubscriber.infrastructure.gateway.MovieGateway;
import br.com.api.usersubscriber.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = UserSubscriberApplication.class)
class UserServiceImplTest {

  @Mock
  UserRepository userRepository;

  @Mock
  MovieGateway movieGateway;

  @InjectMocks
  UserServiceImpl userService;

  @Test
  void given_an_user_with_valid_input_when_the_user_is_created_then_must_return_the_same_values()
      throws InvalidRequestBodyException {
    final User user = new User("John Doe", "1980-10-10", "john.doe@email.com");

    when(userRepository.save(user)).thenReturn(user);

    final User createdUser = userService.create(user);

    assertThat(user.getName()).isEqualTo(createdUser.getName());
    assertThat(user.getEmail()).isEqualTo(createdUser.getEmail());
    assertThat(user.getBirthDate()).isEqualTo(createdUser.getBirthDate());
    assertThat(user.getId()).isEqualTo(createdUser.getId());
  }

  @Test
  void
      given_an_user_with_an_invalid_name_field_when_the_user_is_created_then_must_throw_an_exception() {
    final User user = new User("", "", "");

    final InvalidRequestBodyException ex =
        Assertions.assertThrows(InvalidRequestBodyException.class, () -> userService.create(user));

    assertThat(ex).hasMessage("The name field must not be null or empty.");
    assertThat(ex.getStatusCode()).isEqualTo(422);
  }

  @Test
  void
      given_an_user_with_an_null_name_field_when_the_user_is_created_then_must_throw_an_exception() {
    final User user = new User(null, "", "");

    final InvalidRequestBodyException ex =
        Assertions.assertThrows(InvalidRequestBodyException.class, () -> userService.create(user));

    assertThat(ex).hasMessage("The name field must not be null or empty.");
    assertThat(ex.getStatusCode()).isEqualTo(422);
  }

  @Test
  void
      given_an_user_with_an_invalid_birthDate_field_when_the_user_is_created_then_must_throw_an_exception() {
    final User user = new User("John Doe", "", "");

    final InvalidRequestBodyException ex =
        Assertions.assertThrows(InvalidRequestBodyException.class, () -> userService.create(user));

    assertThat(ex).hasMessage("The birthDate field must not be null or empty.");
    assertThat(ex.getStatusCode()).isEqualTo(422);
  }

  @Test
  void
      given_an_user_with_a_null_birthDate_field_when_the_user_is_created_then_must_throw_an_exception() {
    final User user = new User("John Doe", null, "");

    final InvalidRequestBodyException ex =
        Assertions.assertThrows(InvalidRequestBodyException.class, () -> userService.create(user));

    assertThat(ex).hasMessage("The birthDate field must not be null or empty.");
    assertThat(ex.getStatusCode()).isEqualTo(422);
  }

  @Test
  void
      given_an_user_with_an_invalid_email_field_when_the_user_is_created_then_must_throw_an_exception() {
    final User user = new User("John Doe", "1980-10-10", "");

    final InvalidRequestBodyException ex =
        Assertions.assertThrows(InvalidRequestBodyException.class, () -> userService.create(user));

    assertThat(ex).hasMessage("The email field must not be null or empty.");
    assertThat(ex.getStatusCode()).isEqualTo(422);
  }

  @Test
  void
      given_an_user_with_a_null_email_field_when_the_user_is_created_then_must_throw_an_exception() {
    final User user = new User("John Doe", "1980-10-10", null);

    final InvalidRequestBodyException ex =
        Assertions.assertThrows(InvalidRequestBodyException.class, () -> userService.create(user));

    assertThat(ex).hasMessage("The email field must not be null or empty.");
    assertThat(ex.getStatusCode()).isEqualTo(422);
  }

  @Test
  void given_an_user_when_a_movie_update_is_requested_must_return_a_new_movie_field_value() {
    final User user =
        new User(
            "John Doe",
            "1980-10-10",
            "john.doe@email.com",
            new Movie("001", "Movie test", "Teste", "2020"));

    Movie randomMovie = new Movie("002", "New movie test", "Novo teste", "2020");

    when(movieGateway.getRandomMovie())
        .thenReturn(randomMovie);

    when(userRepository.save(any()))
        .thenReturn(new User(user, randomMovie));

    final User updatedUser = userService.updateUserMovie(user);

    assertThat(updatedUser.getId()).isEqualTo(user.getId());
    assertThat(updatedUser.getMovie().getId()).isNotEqualTo(user.getMovie().getId());
  }
}
