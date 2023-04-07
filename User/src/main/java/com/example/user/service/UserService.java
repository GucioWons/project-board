package com.example.user.service;

import com.example.user.Repository.UserRepository;
import com.example.user.exceptions.ErrorSubmissionException;
import com.example.user.model.NewPass;
import com.example.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getUserById(long id){
        User user=userRepository.existsById(id) ? userRepository.getReferenceById(id) : null;
        if(user!=null){
            return user;
        }
        else{
            throw new ErrorSubmissionException("Not found user id");
        }
    }

    public User registerUser(User user){
        if(user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getEmail().isEmpty() || user.getPassw().isEmpty()){
            throw new ErrorSubmissionException("Empty fields");
        }
        else if(userRepository.existsByEmail(user.getEmail()) || userRepository.existsByPassw(user.getPassw())){
            throw new ErrorSubmissionException("Email or Password already exists");
        }
        else {
            return userRepository.save(user);
        }
    }

    public void editUser(NewPass newData) {
        User user=userRepository.existsById(newData.getUserId()) ? userRepository.getReferenceById(newData.getUserId()) : null;
        if(user!=null){
            if(user.getPassw().equals(newData.getOldPassw())){
                user.setEmail(newData.getEmail());
                user.setPassw(newData.getPassw());
                userRepository.updateEmailAndPassw(user.getEmail(), user.getPassw(), user.getId());
            }
            else{
                throw new ErrorSubmissionException("incorrect password");
            }
        }
        else{
            throw new ErrorSubmissionException("User not found");
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
}
