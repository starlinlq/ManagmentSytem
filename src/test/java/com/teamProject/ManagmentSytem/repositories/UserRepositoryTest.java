package com.teamProject.ManagmentSytem.repositories;

import com.teamProject.ManagmentSytem.entities.Profile;
import com.teamProject.ManagmentSytem.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;
    private final String userNameGood = "starlin";
    private final String userNameBad = "jimmyV";
    private final String emailGood = "starlin@gmail.com";
    private final String emailBad = "jimmyV@aol.com";

    // Fake user for testing
    private final Profile profile = Profile.builder()
            .firstName("Larry")
            .lastName("Smith")
            .salary(20000)
            .overtime(0)
            .medical(0)
            .bonus(0)
            .other(0)
            .totalOvertime(0)
            .ratePerHour(0)
            .build();

    private final User user = User.builder()
            .username("laSmith12")
            .email("archer1@genspark.net")
            .password("password")
            .profile(profile)
            .build();

    // exitsByUserName true/false
    @Test
    void testIfExistsByUsernameFalse() {
        assertFalse(userRepository.existsByUsername(userNameBad));
    }

    @Test
    void testIfExistsByUsernameTrue() {
        assertTrue(userRepository.existsByUsername(userNameGood));
    }

    // UserName exits true/false
    @Test
    void existsByEmailisTrue() {
        assertFalse(userRepository.existsByEmail(emailBad));

    }
    @Test
    void existsByEmailisFalse(){
        assertTrue(userRepository.existsByEmail(emailGood));
    }

    // findByUserName true/false
    @Test
    void testFindByUserNameReturnsUser(){
        Optional<User> user = userRepository.findByUsername(userNameGood);
        assertTrue(user.isPresent());
    }
    @Test
    void testFindByUserNameReturnsNoUser(){
        Optional<User> user = userRepository.findByUsername(userNameBad);
        assertFalse(user.isPresent());
    }

    // Save user information needed for testing







//    @Test
//    public void create(){
//        User user = new User();
//        user.setEmail("test@gmail.com");
//        user.setPassword("");
//        user.setUsername("testing");
//        user.setRoles(List.of("ROLE_USER"));
//        userRepository.save(user);
//    }
}