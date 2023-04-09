package com.example.user.Controller;

import com.example.user.model.User;
import com.example.user.model.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoMapper {
    public UserDtoMapper(){}
    public static List<UserDto> mapToUserDtoList(List<User> users){
        return users.stream()
                .map(UserDtoMapper::mapToUserDto)
                .collect(Collectors.toList());
    }
    public static UserDto mapToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .passw(user.getPassw())
                .build();
    }
}
