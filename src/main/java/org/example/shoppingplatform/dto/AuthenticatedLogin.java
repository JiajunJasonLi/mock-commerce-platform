package org.example.shoppingplatform.dto;

import org.example.shoppingplatform.entity.User;
import org.springframework.security.core.Authentication;

public record AuthenticatedLogin(User user, Authentication authentication) {
}
