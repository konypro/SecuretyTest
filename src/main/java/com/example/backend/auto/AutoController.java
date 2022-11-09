package com.example.backend.auto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/autos")
@RequiredArgsConstructor

public class AutoController {
    private final AutoService autoService;

    @GetMapping String getAutos () {
        return "Auto";
    }
}
