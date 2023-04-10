package com.example.user.service;

import com.example.user.Repository.UserRepository;
import com.example.user.exceptions.ErrorSubmissionException;
import com.example.user.model.ChangeData;
import com.example.user.model.NewUserData;
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

    public void registerUser(NewUserData userData){
        if(userData.getFirstName().isEmpty() || userData.getLastName().isEmpty() || userData.getEmail().isEmpty() || userData.getPassw().isEmpty()){
            throw new ErrorSubmissionException("Empty fields");
        }
        else if(userRepository.existsByEmail(userData.getEmail()) || userRepository.existsByPassw((userData.getPassw()).hashCode())){
            throw new ErrorSubmissionException("Email or Password already exists");
        }
        else {
            User user=new User();
            user.setFirstName(userData.getFirstName());
            user.setLastName(userData.getLastName());
            user.setEmail(userData.getEmail());
            user.setPassw(userData.getPassw().hashCode());
            userRepository.save(user);
        }
    }

    public void editUser(ChangeData changeData ) {
        User user=userRepository.existsById(changeData.getId()) ? userRepository.findById(changeData.getId()) : null;
        if(user!=null && user.getPassw()==changeData.getOldPassw().hashCode()){
            user.setEmail(changeData.getEmail());
            user.setPassw(changeData.getNewPassw().hashCode());
            userRepository.save(user);
        }
        else{
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public List<User> getUserByData(String data) {
        List<User> users=userRepository.findByEmailOrFirstNameOrLastName(data, data, data);
        if(users.isEmpty()){
            throw new ErrorSubmissionException("User not found");
        }
        else{
            return users;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
