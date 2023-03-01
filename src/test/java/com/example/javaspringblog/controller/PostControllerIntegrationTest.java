package com.example.javaspringblog.controller;

import com.example.javaspringblog.entity.dto.CreatePostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PostControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllPostsShouldReturnPosts() throws Exception{
        mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].postId").exists());
    }

    @Test
    void getPostByIdShouldReturnPost() throws Exception {
        mvc.perform(get("/post/42"))
                .andExpect(status().isOk());
    }

    @Test
    void getPostByIdShouldReturn404() throws Exception {
        mvc.perform(get("/post/0"))
                .andExpect(status().isNotFound());
    }
    @Test
    void createPost() throws Exception {

        CreatePostRequest postRequest = new CreatePostRequest();
        postRequest.setPostBody("test post body");
        postRequest.setPostHeader("test post header");

        mvc.perform(post("/post")
                .with(httpBasic("someuser2","somepassword2"))
                .content(objectMapper.writeValueAsString(postRequest))
                .contentType("application/json")
        ).andExpect(status().isOk());

    }
}