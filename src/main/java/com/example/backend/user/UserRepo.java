package com.example.backend.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<AppUser, String> {
    AppUser findByUserName(String username);
}
