package com.example.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeDataDto {
    private Long id;
    private String oldData;
    private String newData;
}
