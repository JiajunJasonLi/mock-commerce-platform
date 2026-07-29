package org.example.shoppingplatform.controller;

import jakarta.servlet.Registration;
import org.example.shoppingplatform.config.SecurityConfig;
import org.example.shoppingplatform.dto.RegistrationRequest;
import org.example.shoppingplatform.entity.User;
import org.example.shoppingplatform.exception.DuplicateEmailException;
import org.example.shoppingplatform.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;


import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
class AuthControllerTest {

    @MockitoBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String USERNAME = "testuser";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "password123";
    private static final String INVALID_EMAIL = "not-an-email";

    private final RegistrationRequest request = new RegistrationRequest();

    @Test
    void register_validRequest_returnCreated() throws Exception {
        // Create mock Request and user

        request.setUsername(USERNAME);
        request.setEmail(EMAIL);
        request.setPassword(PASSWORD);

        User user = new User();
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);

        when(authService.register(any(RegistrationRequest.class)))
                .thenReturn(user);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(USERNAME))
                .andExpect(jsonPath("$.email").value(EMAIL));

        verify(authService).register(argThat(actualRequest ->
                actualRequest.getUsername().equals(USERNAME)
                && actualRequest.getEmail().equals(EMAIL)
                && actualRequest.getPassword().equals(PASSWORD)
        ));
    }

    @Test
    void register_invalidEmail_returnsBadRequest() throws Exception {

        request.setUsername(USERNAME);
        request.setEmail(INVALID_EMAIL);
        request.setPassword(PASSWORD);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(authService, never()).register(any(RegistrationRequest.class));
    }

    @Test
    void register_missingEmail_returnsBadRequest() throws Exception {
        request.setUsername(USERNAME);
        request.setPassword(PASSWORD);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(authService, never()).register(any(RegistrationRequest.class));
    }

    @Test
    void register_invalidUsername_returnsBadRequest() throws Exception {
        request.setEmail(EMAIL);
        request.setPassword(PASSWORD);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(authService, never()).register(any(RegistrationRequest.class));
    }

    @Test
    void register_invalidPassword_returnsBadRequest() throws Exception {

        request.setUsername(USERNAME);
        request.setEmail(INVALID_EMAIL);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(authService, never()).register(any(RegistrationRequest.class));
    }

    @Test
    void register_duplicateEmail_returnsConflict() throws Exception {

        request.setUsername(USERNAME);
        request.setEmail(EMAIL);
        request.setPassword(PASSWORD);

        when(authService.register(any(RegistrationRequest.class)))
                .thenThrow(new DuplicateEmailException("Email already registered"));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());

        verify(authService).register(any(RegistrationRequest.class));
    }
}
