package edu.oleks.security.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
@author   oleksandra
@project   security
@class  UserRepository
@version  1.0.0
@since 01.05.2025 - 16.25
*/
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
