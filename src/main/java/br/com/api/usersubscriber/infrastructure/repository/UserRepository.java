package br.com.api.usersubscriber.infrastructure.repository;

import br.com.api.usersubscriber.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {}
