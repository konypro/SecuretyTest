package com.example.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("login")
    public String loginUser() {

        return "Login Klappt...";
    }


    @GetMapping("logout")
    public void logoutUser(HttpSession httpSession) {
        /*     httpSession.invalidate();*/
    }

}
