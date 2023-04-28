package com.example.user.model;
import java.util.Objects;

public record RegistrationDto(String firstName, String lastName, String email, String passw) {
    public RegistrationDto{
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(email);
        Objects.requireNonNull(passw);
    }
}
