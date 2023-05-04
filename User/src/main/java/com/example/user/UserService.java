package com.example.user;

import com.example.user.mapper.RegToUserMapper;
import com.example.user.mapper.UserToDtoMapper;
import com.example.user.exception.CustomomizeException;
import com.example.user.dto.CredentialsDto;
import com.example.user.dto.RegistrationDto;
import com.example.user.dto.UserDto;
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

    public UserDto changeEmail(long id, String password, String newEmail) {
        return userRepository.findById(id).map(user->saveNewEmail(user, password, newEmail))
                .orElseThrow(()->new CustomomizeException("User id not found"));
    }

    public UserDto saveNewEmail(User user, String password, String newEmail){
        if(BCrypt.checkpw(password, user.getPassword())){
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

    public UserDto changePassw(long id, String password, String newPassword) {
        return userRepository.findById(id).map(user->saveNewPassw(user, password, newPassword))
                .orElseThrow(()->new CustomomizeException("User id not found"));
    }

    public UserDto saveNewPassw(User user, String password, String newPassword){
        if(BCrypt.checkpw(password, user.getPassword())){
            user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
            userRepository.save(user);
            return dtoMapper.mapToDto(user);
        }else{
            throw new CustomomizeException("Incorrect password");
        }
    }

    public UserDto register(RegistrationDto registrationDto) {
        if (registrationDto.firstName().isEmpty() || registrationDto.lastName().isEmpty() ||
                registrationDto.email().isEmpty() || registrationDto.password().isEmpty()) {
            throw new CustomomizeException("Empty user data");
        }
        if (userRepository.existsByEmail(registrationDto.email())) {
            throw new CustomomizeException("Incorrect submitted data");
        } else {
            return dtoMapper.mapToDto(userPreparation(userMapper.mapRegToUser(registrationDto)));
        }
    }

    public User userPreparation(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return user;
    }

    public UserDto login(CredentialsDto credentialsDto) {
        return dtoMapper.mapToDto(userRepository.findByEmail(credentialsDto.email())
                .filter(user->BCrypt.checkpw(credentialsDto.password(), user.getPassword()))
                .orElseThrow(()->new CustomomizeException("Incorect email or password")));
    }
}
