package com.teamProject.ManagmentSytem.controllers;

import com.teamProject.ManagmentSytem.entities.User;
import com.teamProject.ManagmentSytem.exception.UsernameTakenException;
import com.teamProject.ManagmentSytem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody User user){
        boolean userNameTaken  = userService.existsByUsername(user.getUsername());
        boolean userEmailTaken = userService.existsByEmail(user.getEmail());

        if(userNameTaken){
            return new ResponseEntity<>(new UsernameTakenException("Username already taken", "Try a different username"), HttpStatus.BAD_REQUEST);
        }  else if (userEmailTaken){
            return new ResponseEntity<>(new UsernameTakenException("Email already taken", "Try a different email"), HttpStatus.BAD_REQUEST);
        }

        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);

    }


}
