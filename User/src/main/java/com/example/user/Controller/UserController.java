package com.example.user.Controller;

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
    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@RequestBody @PathVariable long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }
    @PostMapping("/register")
    public void registerUser(User userData){
        userService.registerUser(userData);
    }
    @PostMapping("/changeEmail")
    public void changeEmail(@RequestBody long id, String passw, String newEmail){
        userService.changeEmail(id, passw, newEmail);
    }
    @PostMapping("/changePassw")
    public void changePassw(@RequestBody long id, String passw, String newPassw){
        userService.changePassw(id, passw, newPassw);
    }
    @GetMapping("/data")
    public ResponseEntity<List<User>> getUserSearchValue(@RequestBody String searchValue){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserBySearchValue(searchValue));
    }
}
