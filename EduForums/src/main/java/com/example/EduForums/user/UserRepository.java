package com.example.EduForums.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // TODO
    Optional<User> findUserByEmail(String email);
}

