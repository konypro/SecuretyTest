package com.example.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/login")
    public String loginUser() {
        return "Login Klappt...";
    }


    @GetMapping("/logout")
    public String logoutUser(HttpSession httpSession) {
        httpSession.invalidate();
        return "Logout Klappt...";

    }

    @PostMapping()
    public void createUser(@RequestBody NewAppUser newAppUser){
        service.save(newAppUser);
    }

}
