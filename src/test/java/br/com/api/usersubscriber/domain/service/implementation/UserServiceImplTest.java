package br.com.api.usersubscriber.domain.service.implementation;

import br.com.api.usersubscriber.UserSubscriberApplication;
import br.com.api.usersubscriber.domain.entity.User;
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException;
import br.com.api.usersubscriber.domain.service.UserService;
import br.com.api.usersubscriber.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = UserSubscriberApplication.class)
class UserServiceImplTest {

  final UserRepository userRepository = Mockito.mock(UserRepository.class);

  @Test
  void givenAnUserWithValidInputWhenTheUserIsCreatedThenMustReturnTheSameValues()
      throws InvalidRequestBodyException {
    final User user = new User("John Doe", "1980-10-10", "john.doe@email.com");

    when(userRepository.save(user)).thenReturn(user);

    final UserService userService = new UserServiceImpl(userRepository);
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

    final UserService userService = new UserServiceImpl(userRepository);

    InvalidRequestBodyException ex =
        Assertions.assertThrows(InvalidRequestBodyException.class, () -> userService.create(user));

    assertThat(ex).hasMessage("The name field must not be empty.");
    assertThat(ex.getStatusCode()).isEqualTo(422);
  }

  @Test
  void
      given_an_user_with_an_invalid_birthDate_field_when_the_user_is_created_then_must_throw_an_exception() {
    final User user = new User("John Doe", "", "");

    final UserService userService = new UserServiceImpl(userRepository);

    InvalidRequestBodyException ex =
        Assertions.assertThrows(InvalidRequestBodyException.class, () -> userService.create(user));

    assertThat(ex).hasMessage("The birthDate field must not be empty.");
    assertThat(ex.getStatusCode()).isEqualTo(422);
  }

  @Test
  void
      given_an_user_with_an_invalid_email_field_when_the_user_is_created_then_must_throw_an_exception() {
    final User user = new User("John Doe", "1980-10-10", "");

    final UserService userService = new UserServiceImpl(userRepository);

    InvalidRequestBodyException ex =
        Assertions.assertThrows(InvalidRequestBodyException.class, () -> userService.create(user));

    assertThat(ex).hasMessage("The email field must not be empty.");
    assertThat(ex.getStatusCode()).isEqualTo(422);
  }
}
