package com.example.ecommercebackend.api.controller.auth;

import com.example.ecommercebackend.api.model.LoginBody;
import com.example.ecommercebackend.api.model.LoginResponse;
import com.example.ecommercebackend.api.model.RegistrationBody;
import com.example.ecommercebackend.exception.UserAlreadyExistException;
import com.example.ecommercebackend.model.User;
import com.example.ecommercebackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){
        String JWT = userService.loginUser(loginBody);
        if(JWT == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(JWT);
            return ResponseEntity.ok(loginResponse);
        }
    }

    @GetMapping("/me")
    public User getLoggedInUserProfile(@AuthenticationPrincipal User user){
        return user;
    }
}
