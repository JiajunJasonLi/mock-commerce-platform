package org.example.shoppingplatform.config;

import org.example.shoppingplatform.entity.User;
import org.example.shoppingplatform.enums.MembershipTier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String email;
    private final String passwordHash;
    private final MembershipTier membershipTier;

    public CustomUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
        this.membershipTier = user.getMembershipTier();
    }

    public Long getId() {
        return id;
    }

    public String getDisplayUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public MembershipTier getMembershipTier() {
        return membershipTier;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}
