package org.example.shoppingplatform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.shoppingplatform.dto.*;
import org.example.shoppingplatform.entity.User;
import org.example.shoppingplatform.service.AuthService;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<String> loginUser(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse) {

        System.out.println(httpRequest);

        return ResponseEntity.ok("text");

        // Calling the service login for ?
//        AuthenticatedLogin result = authService.login(request);
//
//        // TODO
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//
//        // TODO
//        context.setAuthentication(result.authentication());
//        SecurityContextHolder.setContext(context);
//
//        // TODO
//        securityContextRepository.saveContext(context, httpRequest, httpResponse);
//
//        User user = result.user();
//
//        LoginResponse response = new LoginResponse(
//                user.getUsername(),
//                user.getEmail(),
//                user.getMembershipTier()
//        );
//
//        // TODO: Change the right status code and context
//        return ResponseEntity.ok(response);
    }
}
