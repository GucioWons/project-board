package com.example.user.Controller;

import com.example.user.model.ChangeDataDto;
import com.example.user.model.CredentialsDto;
import com.example.user.model.User;
import com.example.user.model.UserDto;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }
    @PutMapping("/changeEmail")
    public ResponseEntity<UserDto> changeEmail(@RequestBody ChangeDataDto dataDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.changeEmail(dataDto));
    }
    @PutMapping("/changePassw")
    public ResponseEntity<UserDto> changePassw(@RequestBody ChangeDataDto dataDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.changePassw(dataDto));
    }
    @GetMapping("/searchValue")
    public ResponseEntity<List<UserDto>> getUserBySearchValue(@RequestParam String searchValue){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserBySearchValue(searchValue));
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody User user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(user));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(credentialsDto));
    }
}
