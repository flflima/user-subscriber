package br.com.api.usersubscriber.infrastructure.service.implementation;

import br.com.api.usersubscriber.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryImpl extends MongoRepository<User, String> {}
