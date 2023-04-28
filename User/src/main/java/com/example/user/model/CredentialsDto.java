package com.example.user.model;

import java.util.Objects;

public record CredentialsDto(String email, String passw) {
    public CredentialsDto{
        Objects.requireNonNull(email);
        Objects.requireNonNull(passw);
    }
}
