package org.example.shoppingplatform.dto;

import org.example.shoppingplatform.config.CustomUserDetails;
import org.springframework.security.core.Authentication;

public record AuthenticatedLogin(CustomUserDetails userDetails, Authentication authentication) {
}
