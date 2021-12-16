package com.teamProject.ManagmentSytem.repositories;

import com.teamProject.ManagmentSytem.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void create(){
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("");
        user.setUsername("testing");
        user.setRoles(List.of("ROLE_USER"));
        userRepository.save(user);
    }
}