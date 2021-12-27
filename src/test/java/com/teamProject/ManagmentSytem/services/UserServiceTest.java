package com.teamProject.ManagmentSytem.services;

import com.teamProject.ManagmentSytem.dto.UserDto;
import com.teamProject.ManagmentSytem.entities.Profile;
import com.teamProject.ManagmentSytem.entities.User;
import com.teamProject.ManagmentSytem.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    private final String userNameGood = "marlonBrando";
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
    @DisplayName("Testing Service: exitsByUserName ")
    class existsByUserName{
        @Test
        @DisplayName("--expecting false--")
        void testExistsByUsernameFalse() {
            assertFalse(userService.existsByUsername(userNameBad));
        }
        @Test
        @DisplayName("--expecting true --")
        void testExistsByUserTrue(){
            assertTrue(userService.existsByUsername(userNameGood));
        }

    }

    @Nested
    @DisplayName(" Testing Repo: existsByEmail ")
    class TestExistsByEmail{

        @Test
        @DisplayName("-- expecting FALSE --")
        void existsByEmailFalse() {
                assertFalse(userService.existsByEmail(emailBad));
        }
        @Test
        @DisplayName(" -- expecting TRUE --")
        void existsByEmailTrue(){
            assertTrue(userService.existsByEmail(emailGood));
        }
    }

    @Nested
    @DisplayName("Testing basic crud operations for: ")
    class serviceBasicCrudOperations{
        @Transactional
        @Test
        @DisplayName("Insert one user expecting True")
        void testSave() {
            assertDoesNotThrow(()->userService.save(user));
        }

        @Test
        @DisplayName("Find all users")
        void testReadAllUsers() {
//            List<UserDto> users = userService.readAllUsers();
//            users.forEach(System.out::println);
//            assertTrue(users.stream().findFirst().isPresent());
        }

        @Test
        @DisplayName("Find one user")
        void testReadOneUserById() {
            assertTrue(userService.readOneUserById(userNameGood).isPresent());
        }

        @Test
        @DisplayName("delete one user")
        void testRemoveUser() {
            assertDoesNotThrow(()->userService.removeUser(user.getUsername()));
        }

        @Test
        @DisplayName("convert user to DTO")
        void testToUserDto() {

        }
    }
}