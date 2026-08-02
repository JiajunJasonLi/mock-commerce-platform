package org.example.shoppingplatform.dto;

import org.example.shoppingplatform.enums.MembershipTier;

public class LoginResponse {

    private String username;
    private String email;
    private MembershipTier membershipTier;

    public LoginResponse() {

    }

    public LoginResponse(String username, String email,MembershipTier membershipTier) {
        this.membershipTier = membershipTier;
        this.email = email;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public MembershipTier getMembershipTier() {
        return membershipTier;
    }
}
