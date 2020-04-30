package br.com.api.usersubscriber.infrastructure.repository;

import br.com.api.usersubscriber.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByNotifyMeTrue();
}
