package org.example.shoppingplatform.service;

import org.example.shoppingplatform.dto.RegistrationRequest;
import org.example.shoppingplatform.entity.User;
import org.example.shoppingplatform.enums.MembershipTier;
import org.example.shoppingplatform.exception.DuplicateEmailException;
import org.example.shoppingplatform.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

//     public String login(LoginRequest)
}
