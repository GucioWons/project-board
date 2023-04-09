package com.example.user.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
private long id;
private String firstName;
private String lastName;
private String email;
private int passw;
}
