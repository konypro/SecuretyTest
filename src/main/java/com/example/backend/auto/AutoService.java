package com.example.backend.auto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoService {
   private final AutoReposetory autoReposetory;
    List<Auto> findAll(){
        return autoReposetory.findAll();
    }
}
