package br.com.api.usersubscriber.domain.service.implementation


import br.com.api.usersubscriber.domain.entity.User
import br.com.api.usersubscriber.domain.model.exception.InvalidRequestBodyException
import br.com.api.usersubscriber.domain.service.UserService
import br.com.api.usersubscriber.infrastructure.service.implementation.UserRepositoryImpl
import spock.lang.Specification
import spock.lang.Unroll

class UserServiceImplTest extends Specification {
    final UserRepositoryImpl userRepository = Mock(UserRepositoryImpl)

    void "must return created user when all fields are valid"() {
        given: "An user with valid inputs."
        final User user = new User("John Doe", "1980-10-10", "john.doe@email.com")

        when: "The user is created."
        final UserService userService = new UserServiceImpl(userRepository)
        final User createdUser = userService.create(user)

        then: "The returned User values must remain the same."
        1 * userRepository.create(user) >> user

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
        name            | birthDate     | email | message                                   | statusCode
        ""              | ""            | ""    | "The name field must not be empty."       | 422
        "John Doe"      | ""            | ""    | "The birthDate field must not be empty."  | 422
        "John Doe"      | "1980-10-10"  | ""    | "The email field must not be empty."      | 422
    }
}
