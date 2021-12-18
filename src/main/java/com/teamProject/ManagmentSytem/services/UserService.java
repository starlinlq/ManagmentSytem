package com.teamProject.ManagmentSytem.services;

import com.teamProject.ManagmentSytem.entities.User;
import com.teamProject.ManagmentSytem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // Create
    public User save(User user){
        user.setRoles(List.of("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Read All users
    public List<User> readAllUsers(){
        List<User> userList = new ArrayList<>();
        userList.addAll(userRepository.findAll());
        return userList;
    }

    // Read one user by id
    public Optional<User> readOneUserById(String userName){
        return userRepository.findByUsername(userName);
    }

    public void removeUser(String userName){
        userRepository.deleteByUsername(userName);
    }
}
