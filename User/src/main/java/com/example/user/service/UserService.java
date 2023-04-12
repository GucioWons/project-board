package com.example.user.service;

import com.example.user.Repository.UserRepository;
import com.example.user.exceptions.ErrorSubmissionException;
import com.example.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getUserById(long id){
        User user=userRepository.existsById(id) ? userRepository.findById(id) : null;
        if(user!=null){
            return user;
        }
        else{
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public void registerUser(User userData){
        if(userData.getFirstName().isEmpty() || userData.getLastName().isEmpty() || userData.getEmail().isEmpty() || userData.getPassw().isEmpty()){
            throw new ErrorSubmissionException("Empty fields");
        }
        else if(userRepository.existsByEmail(userData.getEmail()) || userRepository.existsByPassw((userData.getPassw()))){
            throw new ErrorSubmissionException("Email or Password already exists");
        }
        else {
            User user=new User();
            user.setFirstName(userData.getFirstName());
            user.setLastName(userData.getLastName());
            user.setEmail(userData.getEmail());
            user.setPassw(userData.getPassw());
            userRepository.save(user);
        }
    }

    public void changeEmail(long id, String passw, String newEmail) {
        User user=userRepository.existsById(id) ? userRepository.findById(id) : null;
        if(user!=null && user.getPassw().equals(passw)){
            user.setEmail(newEmail);
            userRepository.save(user);
        }
        else{
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public List<User> getUserBySearchValue(String searchValue) {
        return userRepository.findByEmailOrFirstNameOrLastName(searchValue, searchValue, searchValue);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void changePassw(long id, String passw, String newPassw) {
        User user=userRepository.existsById(id) ? userRepository.findById(id) : null;
        if(user!=null && user.getPassw().equals(passw)){
            user.setPassw(newPassw);
            userRepository.save(user);
        }
        else{
            throw new ErrorSubmissionException("Not found user id");
        }
    }
}
