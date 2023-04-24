package com.example.user.Controller;

import com.example.user.model.User;
import com.example.user.model.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoConvertion {
    public static List<UserDto> mapToListDto(List<User> users){
        return users.stream()
                .map(UserDtoConvertion::mapToDto)
                .collect(Collectors.toList());
    }
    public static UserDto mapToDto(User user){
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName((user.getLastName()))
                .email(user.getEmail())
                .created(user.getCreated())
                .build();
    }
}
