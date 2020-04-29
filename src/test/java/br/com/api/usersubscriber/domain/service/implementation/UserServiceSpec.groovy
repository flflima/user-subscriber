package br.com.api.usersubscriber.domain.service.implementation

import br.com.api.usersubscriber.UserSubscriberApplication
import br.com.api.usersubscriber.domain.entity.User
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException
import br.com.api.usersubscriber.domain.service.UserService
import br.com.api.usersubscriber.infrastructure.repository.UserRepository
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
@ContextConfiguration(classes = UserSubscriberApplication.class)
class UserServiceSpec extends Specification {

    UserRepository userRepository = Mock(UserRepository)

    void "must return created user when all fields are valid"() {
        given: "An user with valid inputs."
        User user = new User("John Doe", "1980-10-10", "john.doe@email.com")

        when: "The user is created."
        UserService userService = new UserServiceImpl(userRepository)
        User createdUser = userService.create(user)

        then: "The returned User values must remain the same."
        1 * userRepository.save(user) >> user

        verifyAll {
            user.name == createdUser.name
            user.email == createdUser.email
            user.birthDate == createdUser.birthDate
            user.id == createdUser.id
        }
    }

    @Unroll
    void "must throw an exception when a field has an invalid value"() {
        given: "An user with an invalid field."
        final User user = new User(name, birthDate, email)

        when: "The user is created."
        final UserService userService = new UserServiceImpl(userRepository)
        userService.create(user)

        then: "Must throw an Exception."
        InvalidRequestBodyException e = thrown()
        e.message == message
        e.statusCode == statusCode

        where:
        name       | birthDate    | email | message                                  | statusCode
        ""         | ""           | ""    | "The name field must not be empty."      | 422
        "John Doe" | ""           | ""    | "The birthDate field must not be empty." | 422
        "John Doe" | "1980-10-10" | ""    | "The email field must not be empty."     | 422
    }
}
