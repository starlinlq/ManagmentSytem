package com.teamProject.ManagmentSytem.services;

import com.teamProject.ManagmentSytem.dto.UserDto;
import com.teamProject.ManagmentSytem.entities.User;
import com.teamProject.ManagmentSytem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    // Create
    @Transactional
    public User save(User user){
        user.setRoles(List.of("ROLE_USER"));
        return userRepository.save(user);
    }

    // Read All users
    @Transactional
    public List<UserDto> readAllUsers(){
        return  userRepository.findAll().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    // Read one user by id
    @Transactional
    public Optional<User> readOneUserById(String userName){
        return userRepository.findByUsername(userName);
    }

    @Transactional
    public void removeUser(String userName){
        userRepository.deleteByUsername(userName);
    }

    public UserDto toUserDto(User user){return UserMapper.toUserDto(user);}

    public static class UserMapper {
        static UserDto toUserDto(User user){
            return UserDto.builder()
                    .roles(user.getRoles())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
        }
    }
}
