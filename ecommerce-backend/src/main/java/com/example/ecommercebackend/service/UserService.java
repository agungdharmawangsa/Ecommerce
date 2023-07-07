package com.example.ecommercebackend.service;

import com.example.ecommercebackend.api.model.LoginBody;
import com.example.ecommercebackend.api.model.RegistrationBody;
import com.example.ecommercebackend.exception.UserAlreadyExistException;
import com.example.ecommercebackend.model.User;
import com.example.ecommercebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    public UserService (UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService){
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }
    public User registerUser (RegistrationBody registrationBody) throws UserAlreadyExistException {
        // validate email and username exists
        if(userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent() ||
                userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }
        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setFirstname(registrationBody.getFirstname());
        user.setLastname(registrationBody.getLastname());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(LoginBody loginBody){
        Optional<User> appUser = userRepository.findByUsernameIgnoreCase(loginBody.getUsername());

        if(appUser.isPresent()){
            User user = appUser.get();
            if(encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
