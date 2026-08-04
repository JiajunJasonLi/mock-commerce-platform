package org.example.shoppingplatform.controller;

import org.example.shoppingplatform.config.CustomUserDetails;
import org.example.shoppingplatform.dto.ProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
            ) {
        ProfileResponse response = new ProfileResponse(
                userDetails.getDisplayUsername(),
                userDetails.getEmail(),
                userDetails.getMembershipTier()
        );

        return ResponseEntity.ok(response);
    }
}
