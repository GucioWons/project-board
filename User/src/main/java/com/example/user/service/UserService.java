package com.example.user.service;

import com.example.user.Controller.UserDtoMapper;
import com.example.user.Repository.UserRepository;
import com.example.user.exceptions.ErrorSubmissionException;
import com.example.user.model.CredentialsDto;
import com.example.user.model.RegistrationDto;
import com.example.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper dtoMapper;
    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(dtoMapper::mapToDto)
                .orElseThrow(()->new ErrorSubmissionException("Not found user id"));
    }

    public UserDto changeEmail(long id, String passw, String newEmail) {
        return userRepository.findById(id)
                .filter(user->BCrypt.checkpw(passw, user.getPassw())).map(user->{
                    user.setEmail(newEmail);
                    userRepository.save(user);
                    return dtoMapper.mapToDto(user);
                        }).orElseThrow(()->new ErrorSubmissionException("User id not found"));
    }

    public List<UserDto> getUserBySearchValue(String searchValue) {
        return dtoMapper.mapToListDto(userRepository.findUserByEmailOrFirstNameOrLastName(
                searchValue, searchValue, searchValue).stream().toList());
    }

    public List<UserDto> getAllUsers() {
        return dtoMapper.mapToListDto(userRepository.findAll());
    }

    public UserDto changePassw(long id, String passw, String newPassw) {
        return userRepository.findById(id)
                .filter(user -> BCrypt.checkpw(passw, user.getPassw())).map(user -> {
                    user.setPassw(BCrypt.hashpw(newPassw, BCrypt.gensalt()));
                    userRepository.save(user);
                    return dtoMapper.mapToDto(user);
                }).orElseThrow(()->new ErrorSubmissionException("User id not found"));
    }

    public UserDto register(RegistrationDto registrationDto) {
        if(registrationDto.getFirstName().isEmpty() || registrationDto.getLastName().isEmpty() ||
                registrationDto.getEmail().isEmpty() || registrationDto.getPassw().isEmpty()){
            throw new ErrorSubmissionException("Empty user data");
        }
       if(userRepository.existsByEmail(registrationDto.getEmail())){
           throw new ErrorSubmissionException("Email already exists");
       }
       else{
           registrationDto.setPassw(BCrypt.hashpw(registrationDto.getPassw(), BCrypt.gensalt()));
           return dtoMapper.mapToDto(userRepository.save(dtoMapper.mapRegToUser(registrationDto)));
       }
    }

    public UserDto login(CredentialsDto credentialsDto) {
        return dtoMapper.mapToDto(userRepository.findByEmail(credentialsDto.getEmail())
                .filter(user->BCrypt.checkpw(credentialsDto.getPassw(), user.getPassw()))
                .orElseThrow(()->new ErrorSubmissionException("Incorect email or password")));
    }
}
