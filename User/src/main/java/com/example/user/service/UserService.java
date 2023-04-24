package com.example.user.service;

import com.example.user.Controller.UserDtoConvertion;
import com.example.user.Repository.UserRepository;
import com.example.user.exceptions.ErrorSubmissionException;
import com.example.user.model.ChangeDataDto;
import com.example.user.model.CredentialsDto;
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
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {;
            return UserDtoConvertion.mapToDto(user.get());
        } else {
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public UserDto changeEmail(ChangeDataDto dataDto) {
        Optional<User> user = userRepository.findById(dataDto.getId());
        if (user.isPresent() && BCrypt.checkpw(dataDto.getOldData(), user.get().getPassw())) {
            user.get().setEmail(dataDto.getNewData());
            userRepository.save(user.get());
            return UserDtoConvertion.mapToDto(user.get());
        } else {
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public List<UserDto> getUserBySearchValue(String searchValue) {
        Set<User> users=userRepository.findUserByEmailOrFirstNameOrLastName(
                searchValue, searchValue, searchValue);
        return UserDtoConvertion.mapToListDto(users.stream().toList());
    }

    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        return UserDtoConvertion.mapToListDto(users);
    }

    public UserDto changePassw(ChangeDataDto dataDto) {
        Optional<User> user=userRepository.findById(dataDto.getId());
        if(user.isPresent() && BCrypt.checkpw(dataDto.getOldData(), user.get().getPassw())){
            user.get().setPassw(dataDto.getNewData());
            userRepository.save(user.get());
            return UserDtoConvertion.mapToDto(user.get());
        }
        else{
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public UserDto register(User newUserData) {
        String hashedPassw=BCrypt.hashpw(newUserData.getPassw(), BCrypt.gensalt());
        if(userRepository.existsByEmail(newUserData.getEmail())){
            throw new ErrorSubmissionException("Email address is already exists");
        }
        else{
            newUserData.setActive(true);
            newUserData.setPassw(hashedPassw);
            userRepository.save(newUserData);
            return UserDtoConvertion.mapToDto(newUserData);
        }
    }

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.existsByEmail(credentialsDto.getEmail()) ? userRepository.findByEmail(credentialsDto.getEmail()) : null;
        if(user!=null){
            if(BCrypt.checkpw(credentialsDto.getPassw(), user.getPassw())){
               return UserDtoConvertion.mapToDto(user);
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
