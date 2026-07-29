package org.example.shoppingplatform.dto;

public class RegistrationResponse {
    private String username;
    private String email;

    public RegistrationResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
