package br.com.api.usersubscriber.domain.service.implementation

import br.com.api.usersubscriber.domain.entity.User
import br.com.api.usersubscriber.domain.service.UserService
import br.com.api.usersubscriber.infrastructure.service.UserRepository
import br.com.api.usersubscriber.infrastructure.service.implementation.UserRepositoryImpl
import spock.lang.Specification

class UserServiceImplTest extends Specification {
    final UserRepositoryImpl userRepository = Mock(UserRepositoryImpl)

    void "must return created user when all inputs are valid"() {
        given: "An user with valid inputs."
        final User user = new User("John Doe", "1980-10-10", "john.doe@email.com")

        when: "The user is created in database."
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
}
