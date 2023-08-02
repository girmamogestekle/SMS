package com.ce.spring.sms.repository;

import com.ce.spring.sms.domain.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository(value = "userRepository")
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByUsernameAndEnabled(String username, boolean isEnabled);
}
