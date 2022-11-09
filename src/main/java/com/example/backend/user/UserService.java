package com.example.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public void login(){
        userRepo.save(new AppUser(UUID.randomUUID().toString(),"test",new BCryptPasswordEncoder().encode("password")));
    }

    public  AppUser  findByUserName(String username) {
        return userRepo.findByUserName(username);

    }
}
