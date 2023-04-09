package com.example.user.Controller;

import com.example.user.model.ChangeData;
import com.example.user.model.NewUserData;
import com.example.user.model.User;
import com.example.user.model.UserDto;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@RequestBody @PathVariable long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }
    @GetMapping("/user/register")
    public void registerUser(NewUserData userData){
        userService.registerUser(userData);
    }
    @GetMapping("/user/edit")
    public void editUser(ChangeData changeData){
        userService.editUser(changeData);
    }
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUserByData(@RequestBody String data){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserByData(data));
    }
}
