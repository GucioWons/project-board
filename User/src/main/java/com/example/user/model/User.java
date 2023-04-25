package com.example.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    private String passw;
    private LocalDateTime created;
    private boolean active;

   /* public User(String firstName, String lastName, String email, String hashedPassw, LocalDateTime created, boolean active) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.passw=hashedPassw;
        this.created=created;
        this.active=active;
    }*/
}