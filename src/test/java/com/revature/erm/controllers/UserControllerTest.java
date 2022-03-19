package com.revature.erm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.dtos.requests.NewUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;
    private final WebApplicationContext context;
    private final ObjectMapper mapper;

    @Autowired
    public UserControllerTest(WebApplicationContext context, ObjectMapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test_register_returns201_givenValidNewRegistrationRequest() throws Exception {

        // Arrange
        NewUserRequest request = new NewUserRequest();
        request.setFirstname("test");
        request.setLastname("tester");
        request.setEmail("test@revature.com");
        request.setUsername("test123");
        request.setPassword("p4$$word");

        String requestPayload = mapper.writeValueAsString(request);

        // Act and Assert
        MvcResult result = mockMvc.perform(post("/users").contentType("application/json").content(requestPayload))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();

        assertEquals("application/json", result.getResponse().getContentType());


    }

    @Test
    public void test_register_returns400_givenInvalidNewRegistrationRequest() throws Exception {

        // Arrange
        NewUserRequest request = new NewUserRequest();
        request.setLastname("t0st");
        request.setLastname("mctest");
        request.setEmail("test@revature.com");
        request.setUsername("test0123");
        request.setPassword("p4$$word");

        String requestPayload = mapper.writeValueAsString(request);

        // Act and Assert
        MvcResult result = mockMvc.perform(post("/users").contentType("application/json").content(requestPayload))
                .andDo(print())
                .andExpect(status().is(400))
                .andReturn();



    }

}
