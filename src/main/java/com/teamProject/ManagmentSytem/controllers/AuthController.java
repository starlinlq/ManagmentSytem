package com.teamProject.ManagmentSytem.controllers;

import com.teamProject.ManagmentSytem.entities.User;
import com.teamProject.ManagmentSytem.exception.EmployeeNotFoundException;
import com.teamProject.ManagmentSytem.exception.UsernameTakenException;
import com.teamProject.ManagmentSytem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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

    // return all users
    @GetMapping("/")
    public ResponseEntity<?> readAll(){
        try{
            List<User> users = userService.readAllUsers();
            return new ResponseEntity<>(users,HttpStatus.OK);
        } catch (RuntimeException err){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Return a single user
    @GetMapping("/user")
    public ResponseEntity<?> readOne(@RequestParam String user ){
        try{
            Optional<User> userName = userService.readOneUserById(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (RuntimeException err){
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a single user
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteOne(@RequestParam String user){
        try{
            boolean userNameExists = userService.existsByUsername(user);
            if (!userNameExists){
                throw new EmployeeNotFoundException();
            }
            userService.removeUser(user);
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
        } catch (RuntimeException err){
            return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
        }
    }

}
