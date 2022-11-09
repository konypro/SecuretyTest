package com.example.backend.user;

import com.example.backend.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;


    public  AppUser  findByUserName(String username) {
        return userRepo.findByUserName(username);

    }

    public void save(NewAppUser newAppUser) {
        String hashedPassword = SecurityConfig.passwordEncoder.encode(newAppUser.password());
        String id = UUID.randomUUID().toString();
        String userName = newAppUser.userName();

        AppUser appUser = new AppUser(id,userName, hashedPassword);

        userRepo.save(appUser);

    }
}
