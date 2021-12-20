package com.teamProject.ManagmentSytem.repositories;

import com.teamProject.ManagmentSytem.entities.Profile;
import com.teamProject.ManagmentSytem.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    private Profile profile;
    private User user;

    @BeforeEach
    void init(){
        profile = Profile.builder()
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

        user = User.builder()
                .username("laSmith12")
                .email("archer1@genspark.net")
                .password("password")
                .profile(profile)
                .build();

    }

    @Nested
    @DisplayName("Testing Repo : exitsByUserName ")
    class exitsByUserName{
        @Test
        @DisplayName("--expecting FALSE--")
        void testIfExistsByUsernameFalse() {
            assertFalse(userRepository.existsByUsername(userNameBad));
        }

        @Test
        @DisplayName("--expecting TRUE--")
        void testIfExistsByUsernameTrue() {
            assertTrue(userRepository.existsByUsername(userNameGood));
        }
    }


    @Nested
    @DisplayName("Testing Repo: ")
    class userExitsByEmail{
        @Test
        @DisplayName("existsByEmail --expecting FALSE--")
        void existsByEmailisTrue() {
            assertFalse(userRepository.existsByEmail(emailBad));

        }
        @Test
        @DisplayName("exitsByEmail --expecting TRUE--")
        void existsByEmailisFalse(){
            assertTrue(userRepository.existsByEmail(emailGood));
        }
    }
    // UserName exits true/false


    @Nested
    @DisplayName("Testing Repo: findByUserName")
    class FindUserRepotest{
        @Test
        @DisplayName("--expecting TRUE--")
        void testFindByUserNameReturnsUser(){
            Optional<User> user = userRepository.findByUsername(userNameGood);
            System.out.println("user.get() = " + user.get());
            assertTrue(user.isPresent());
        }
        @Test
        @DisplayName("--expecting FALSE--")
        void testFindByUserNameReturnsNoUser(){
            Optional<User> user = userRepository.findByUsername(userNameBad);
            assertFalse(user.isPresent());
        }
    }
    @Nested
    @DisplayName("Testing basic Crud Operations for: ")
    class basicCrudOperations{

        @Test
        @DisplayName("find all users")
        void testFindAllUsers(){
            List<User> users = userRepository.findAll();
            users.forEach(user-> System.out.println(user.getUsername()));
            assertTrue(users.size()!=0,"There should be something in the list");
        }

        @Transactional
        @Test
        @DisplayName("insert one user")
        void testFindOneUser(){
            assertDoesNotThrow(()-> userRepository.save(user));
        }

        @Test
        @DisplayName("find one user")
        void findOneUser(){
            assertTrue(userRepository.findByUsername(userNameGood).isPresent());
        }

        @Test
        @DisplayName("delete one user")
        void deleteOneUser(){
            assertDoesNotThrow(()->userRepository.delete(user));
        }
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