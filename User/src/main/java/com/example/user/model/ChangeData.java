package com.example.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeData {
    private long id;
    private String email;
    private String newPassw;
    private String oldPassw;
}
