package com.example.javaspringblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllUsersByAdminShouldOk() throws Exception {
        mvc.perform(get("/users")
                        .with(httpBasic("someuser2","somepassword2")))
                        .andExpect(status().isOk());
    }
    @Test
    void getAllUsersByUserShouldNotAuthorized() throws Exception {
        mvc.perform(get("/users")
                        .with(httpBasic("abc12345","abc12345")))
                .andExpect(status().isUnauthorized());
    }

}