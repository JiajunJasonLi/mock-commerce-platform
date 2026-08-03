package org.example.shoppingplatform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.shoppingplatform.config.CustomUserDetails;
import org.example.shoppingplatform.dto.*;
import org.example.shoppingplatform.entity.User;
import org.example.shoppingplatform.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final SecurityContextRepository securityContextRepository;

    public AuthController(AuthService authService, SecurityContextRepository securityContextRepository) {
        this.authService = authService;
        this.securityContextRepository = securityContextRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest request) {
        User user = this.authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RegistrationResponse(user.getUsername(), user.getEmail())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse) {

        // Calling the service login for returning the authentication object and user information
        AuthenticatedLogin result = authService.login(request);

        // Create context to store the session
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        // The context of the authentication
        context.setAuthentication(result.authentication());
        SecurityContextHolder.setContext(context);

        securityContextRepository.saveContext(context, httpRequest, httpResponse);

        CustomUserDetails user = result.userDetails();

        LoginResponse response = new LoginResponse(
                user.getDisplayUsername(),
                user.getEmail(),
                user.getMembershipTier()
        );

        return ResponseEntity.ok(response);
    }
}
