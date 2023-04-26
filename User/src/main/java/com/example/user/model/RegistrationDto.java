package com.example.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String passw;
}
