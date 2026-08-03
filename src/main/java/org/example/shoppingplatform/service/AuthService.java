package org.example.shoppingplatform.service;

import org.example.shoppingplatform.config.CustomUserDetails;
import org.example.shoppingplatform.dto.AuthenticatedLogin;
import org.example.shoppingplatform.dto.LoginRequest;
import org.example.shoppingplatform.dto.RegistrationRequest;
import org.example.shoppingplatform.entity.User;
import org.example.shoppingplatform.enums.MembershipTier;
import org.example.shoppingplatform.exception.DuplicateEmailException;
import org.example.shoppingplatform.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User register(RegistrationRequest request) {
        // Validate duplicate email
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already registered");
        }

        // Hash password from request
        String hashPassword = this.passwordEncoder.encode(request.getPassword());

        // Initialize user and saved the info from request to user
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(hashPassword);
        user.setMembershipTier(MembershipTier.REGULAR);

        return userRepository.save(user);
    }

     public AuthenticatedLogin login(LoginRequest request) {
         Authentication authentication = authenticationManager.authenticate(
                 UsernamePasswordAuthenticationToken.unauthenticated(
                         request.getEmail(),
                         request.getPassword()
                 ));

         CustomUserDetails userDetails =
                 (CustomUserDetails) authentication.getPrincipal();

         return new AuthenticatedLogin(userDetails, authentication);
     }
}
