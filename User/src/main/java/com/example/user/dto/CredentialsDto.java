package com.example.user.dto;

import java.util.Objects;

public record CredentialsDto(String email, String password) {
    public CredentialsDto{
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
    }
}
