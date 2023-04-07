package com.example.user.Controller;

import com.example.user.model.NewPass;
import com.example.user.model.User;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@RequestBody @PathVariable long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }
    @GetMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(user));
    }
    @GetMapping("/user/edit")
    public void editUser(@RequestBody NewPass newData){
        userService.editUser(newData);
    }
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUserByData(@RequestBody String data){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserByData(data));
    }
}
