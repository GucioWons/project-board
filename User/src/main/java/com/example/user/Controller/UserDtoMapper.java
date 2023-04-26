package com.example.user.Controller;

import com.example.user.model.RegistrationDto;
import com.example.user.model.User;
import com.example.user.model.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserDtoMapper {
    public List<UserDto> mapToListDto(List<User> users){
        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    public UserDto mapToDto(User user){
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName((user.getLastName()))
                .email(user.getEmail())
                .build();
    }
    public User mapRegToUser(RegistrationDto regDto){
        return User.builder()
                .firstName(regDto.getFirstName())
                .lastName(regDto.getLastName())
                .email(regDto.getEmail())
                .passw(regDto.getPassw())
                .created(LocalDateTime.now())
                .active(true)
                .build();
    }
}
