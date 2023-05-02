package com.example.user.mapper;

import com.example.user.Models.RegistrationDto;
import com.example.user.Models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class RegToUserMapper {
    public User mapRegToUser(RegistrationDto registrationDto){
        return User.builder()
                .firstName(registrationDto.firstName())
                .lastName(registrationDto.lastName())
                .email(registrationDto.email())
                .password(registrationDto.password())
                .created(LocalDateTime.now())
                .active(true)
                .build();
    }
}
