package com.example.user.mapper;

import com.example.user.model.User;
import com.example.user.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserToDtoMapper {
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
}
