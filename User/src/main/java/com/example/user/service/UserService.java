package com.example.user.service;

import com.example.user.mapper.RegToUserMapper;
import com.example.user.mapper.UserToDtoMapper;
import com.example.user.Repository.UserRepository;
import com.example.user.exceptions.CustomomizeException;
import com.example.user.model.CredentialsDto;
import com.example.user.model.RegistrationDto;
import com.example.user.model.User;
import com.example.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserToDtoMapper dtoMapper;
    private final RegToUserMapper userMapper;
    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(dtoMapper::mapToDto)
                .orElseThrow(()->new CustomomizeException("Not found user id"));
    }

    public UserDto changeEmail(long id, String passw, String newEmail) {
        return userRepository.findById(id).map(user->saveNewEmail(user, passw, newEmail))
                .orElseThrow(()->new CustomomizeException("User id not found"));
    }

    public UserDto saveNewEmail(User user, String passw, String newEmail){
        if(BCrypt.checkpw(passw, user.getPassw())){
            user.setEmail(newEmail);
            userRepository.save(user);
            return dtoMapper.mapToDto(user);
        } else{
          throw new CustomomizeException("Incorrect password");
        }
    }

    public List<UserDto> getUserBySearchValue(String searchValue) {
        return dtoMapper.mapToListDto(userRepository.findUserByEmailOrFirstNameOrLastName(
                searchValue, searchValue, searchValue));
    }

    public List<UserDto> getAllUsers() {
        return dtoMapper.mapToListDto(userRepository.findAll());
    }

    public UserDto changePassw(long id, String passw, String newPassw) {
        return userRepository.findById(id).map(user->saveNewPassw(user, passw, newPassw))
                .orElseThrow(()->new CustomomizeException("User id not found"));
    }

    public UserDto saveNewPassw(User user, String passw, String newPassw){
        if(BCrypt.checkpw(passw, user.getPassw())){
            user.setPassw(BCrypt.hashpw(newPassw, BCrypt.gensalt()));
            userRepository.save(user);
            return dtoMapper.mapToDto(user);
        }else{
            throw new CustomomizeException("Incorrect password");
        }
    }

    public UserDto register(RegistrationDto registrationDto) {
        if (registrationDto.firstName().isEmpty() || registrationDto.lastName().isEmpty() ||
                registrationDto.email().isEmpty() || registrationDto.passw().isEmpty()) {
            throw new CustomomizeException("Empty user data");
        }
        if (userRepository.existsByEmail(registrationDto.email())) {
            throw new CustomomizeException("Incorrect submitted data");
        } else {
            return dtoMapper.mapToDto(userPreparation(userMapper.mapRegToUser(registrationDto)));
        }
    }

    public User userPreparation(User user){
        user.setPassw(BCrypt.hashpw(user.getPassw(), BCrypt.gensalt()));
        userRepository.save(user);
        return user;
    }

    public UserDto login(CredentialsDto credentialsDto) {
        return dtoMapper.mapToDto(userRepository.findByEmail(credentialsDto.email())
                .filter(user->BCrypt.checkpw(credentialsDto.passw(), user.getPassw()))
                .orElseThrow(()->new CustomomizeException("Incorect email or password")));
    }
}
