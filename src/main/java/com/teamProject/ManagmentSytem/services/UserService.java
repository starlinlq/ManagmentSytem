package com.teamProject.ManagmentSytem.services;

import com.teamProject.ManagmentSytem.entities.User;
import com.teamProject.ManagmentSytem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User save(User user){
        user.setRoles(List.of("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public ResponseEntity<?> getAllUsers(){
        try{
            List<User> userList = userRepository.findAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch(RuntimeException err){
            return new ResponseEntity<>("Unable to access User list",HttpStatus.NOT_FOUND);
        }
    }
}
