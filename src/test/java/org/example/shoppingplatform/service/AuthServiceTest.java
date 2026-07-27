package org.example.shoppingplatform.service;

import org.example.shoppingplatform.dto.RegistrationRequest;
import org.example.shoppingplatform.entity.User;
import org.example.shoppingplatform.enums.MembershipTier;
import org.example.shoppingplatform.exception.DuplicateEmailException;
import org.example.shoppingplatform.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private static final String USERNAME = "testuser";
    private static final String EMAIL = "test@example.com";
    private static final String PASSWORD = "password123";
    private static final String PASSWORD_HASH = "hashed-password";

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private RegistrationRequest request;

    @BeforeEach()
    void Setup() {
        request = new RegistrationRequest();
        request.setUsername(USERNAME);
        request.setEmail(EMAIL);
        request.setPassword(PASSWORD);
    }

    private void mockSuccessfulRegistration() {
        when(userRepository.existsByEmail(EMAIL))
                .thenReturn(false);

        when(passwordEncoder.encode(PASSWORD))
                .thenReturn(PASSWORD_HASH);

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void register_validRequest_savesUser() {
        mockSuccessfulRegistration();

        User savedUser = authService.register(request);

        assertEquals(USERNAME, savedUser.getUsername());
        assertEquals(EMAIL, savedUser.getEmail());
        assertEquals(PASSWORD_HASH, savedUser.getPasswordHash());
        assertEquals(MembershipTier.REGULAR, savedUser.getMembershipTier());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_duplicateEmail_throwsException() {

        when(userRepository.existsByEmail(EMAIL))
                .thenReturn(true);

        assertThrows(
                DuplicateEmailException.class,
                () -> authService.register(request)
        );

        verify(userRepository, never())
                .save(any(User.class));
    }

    @Test
    void register_validRequest_hashesPassword() {
        mockSuccessfulRegistration();

        User savedUser = authService.register(request);

        verify(passwordEncoder).encode(request.getPassword());

        assertEquals(PASSWORD_HASH, savedUser.getPasswordHash());
    }
}
