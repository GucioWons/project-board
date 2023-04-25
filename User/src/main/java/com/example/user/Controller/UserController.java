package com.example.user.Controller;

import com.example.user.model.CredentialsDto;
import com.example.user.model.RegistrationDto;
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
    public ResponseEntity<UserDto> changeEmail(@RequestParam long id, @RequestParam String passw,
                                               @RequestParam String newEmail){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.changeEmail(id, passw, newEmail));
    }
    @PutMapping("/changePassw")
    public ResponseEntity<UserDto> changePassw(@RequestParam long id, @RequestParam String passw,
                                               @RequestParam String newPassw){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.changePassw(id, passw, newPassw));
    }
    @GetMapping("/searchValue")
    public ResponseEntity<List<UserDto>> getUserBySearchValue(@RequestParam String searchValue){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserBySearchValue(searchValue));
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegistrationDto registrationDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(registrationDto));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(credentialsDto));
    }
}
