package com.example.user.mapper;

import com.example.user.model.RegistrationDto;
import com.example.user.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class RegToUserMapper {
    public User mapRegToUser(RegistrationDto regDto){
        return User.builder()
                .firstName(regDto.firstName())
                .lastName(regDto.lastName())
                .email(regDto.email())
                .passw(regDto.passw())
                .created(LocalDateTime.now())
                .active(true)
                .build();
    }
}
