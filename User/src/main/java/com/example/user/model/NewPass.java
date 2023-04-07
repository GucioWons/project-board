package com.example.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NewPass {
    @Id
    private long id;
    private long userId;
    private String email;
    private String passw;
    private String oldPassw;
}
