package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest

public class SecuretyConfigTest {


            @Autowired
            MockMvc mockMvc;

            @Test
            void expect401_withoutValidUser() throws Exception {
                mockMvc.perform(get("/api/autos"))
                        .andExpect(status().is(401));
            }

            @Test
            @WithMockUser
            void expect401_withValidUser() throws Exception {
                mockMvc.perform(get("/api/autos"))
                        .andExpect(status().is(200));
            }
        }
