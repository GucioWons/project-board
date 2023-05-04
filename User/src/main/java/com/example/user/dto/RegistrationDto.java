package com.example.user.dto;
import java.util.Objects;

public record RegistrationDto(String firstName, String lastName, String email, String password) {
    public RegistrationDto{
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
    }
}
