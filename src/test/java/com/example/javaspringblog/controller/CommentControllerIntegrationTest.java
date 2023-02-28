package com.example.javaspringblog.controller;

import com.example.javaspringblog.entity.dto.CreateCommentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CommentControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void showAllCommentsOnPost() throws Exception {
        mvc.perform(get("/comments/42"))
                .andExpect(status().isOk());
    }

    @Test
    void saveComment() throws Exception {
        CreateCommentRequest commentRequest = new CreateCommentRequest();
        commentRequest.setCommentBody("Test comment body");
        commentRequest.setPostId(42);

        mvc.perform(put("/newcomment")
                .with(httpBasic("someuser2","somepassword2"))
                .content(objectMapper.writeValueAsString(commentRequest))
                .contentType("application/json")
        ).andExpect(status().isOk());
    }
}