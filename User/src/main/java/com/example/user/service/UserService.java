package com.example.user.service;

import com.example.user.Controller.UserDtoConversion;
import com.example.user.Repository.UserRepository;
import com.example.user.exceptions.ErrorSubmissionException;
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
    private final UserDtoConversion dtoConversion;
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return dtoConversion.mapToDto(user.get());
        } else {
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public UserDto changeEmail(long id, String passw, String newEmail) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && BCrypt.checkpw(passw, user.get().getPassw())) {
            user.get().setEmail(newEmail);
            userRepository.save(user.get());
            return dtoConversion.mapToDto(user.get());
        } else {
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public List<UserDto> getUserBySearchValue(String searchValue) {
        Set<User> users=userRepository.findUserByEmailOrFirstNameOrLastName(
                searchValue, searchValue, searchValue);
        return dtoConversion.mapToListDto(users.stream().toList());
    }

    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        return dtoConversion.mapToListDto(users);
    }

    public UserDto changePassw(long id, String passw, String newPassw) {
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent() && BCrypt.checkpw(passw, user.get().getPassw())){
            user.get().setPassw(newPassw);
            userRepository.save(user.get());
            return dtoConversion.mapToDto(user.get());
        }
        else{
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public UserDto register(RegistrationDto registrationDto) {
        if(registrationDto.getFirstName().isEmpty() || registrationDto.getLastName().isEmpty() ||
                registrationDto.getEmail().isEmpty() || registrationDto.getPassw().isEmpty()){
            throw new ErrorSubmissionException("Empty user data");
        }
        else{
            if(userRepository.existsByEmail(registrationDto.getEmail())){
                throw new ErrorSubmissionException("Email address is already exists");
            }
            else{
                User user=dtoConversion.mapRegToUser(registrationDto);
                user.setActive(true);
                user.setPassw(BCrypt.hashpw(registrationDto.getPassw(), BCrypt.gensalt()));
                userRepository.save(user);
                return dtoConversion.mapToDto(user);
            }
        }

    }

    public UserDto login(CredentialsDto credentialsDto) {
        Optional<User> user = userRepository.findByEmail(credentialsDto.getEmail());
        if(user.isPresent()){
            if(BCrypt.checkpw(credentialsDto.getPassw(), user.get().getPassw())){
               return dtoConversion.mapToDto(user.get());
            }
            else{
                throw new ErrorSubmissionException("Wrong email or password");
            }
        }
        else{
            throw new ErrorSubmissionException("User not found");
        }
    }
}
