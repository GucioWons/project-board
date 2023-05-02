package com.example.user.Controller;

import com.example.user.Models.CredentialsDto;
import com.example.user.Models.RegistrationDto;
import com.example.user.Models.UserDto;
import com.example.user.Service.UserService;
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
    @PutMapping("/change-email")
    public ResponseEntity<UserDto> changeEmail(@RequestParam long id, @RequestParam String password,
                                               @RequestParam String newEmail){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.changeEmail(id, password, newEmail));
    }

    @PutMapping("/change-passw")
    public ResponseEntity<UserDto> changePassw(@RequestParam long id, @RequestParam String password,
                                               @RequestParam String newPassword){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.changePassw(id, password, newPassword));
    }

    @GetMapping("/search")
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
