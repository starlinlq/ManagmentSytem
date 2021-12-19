package com.teamProject.ManagmentSytem.controllers;

import com.teamProject.ManagmentSytem.config.UserDetailsImpl;
import com.teamProject.ManagmentSytem.dto.UserDto;
import com.teamProject.ManagmentSytem.entities.User;
import com.teamProject.ManagmentSytem.exception.EmployeeNotFoundException;
import com.teamProject.ManagmentSytem.exception.UsernameTakenException;
import com.teamProject.ManagmentSytem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.teamProject.ManagmentSytem.util.JwtTokenUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody User user){
        boolean userNameTaken  = userService.existsByUsername(user.getUsername());
        boolean userEmailTaken = userService.existsByEmail(user.getEmail());

        if(userNameTaken){
            return new ResponseEntity<>(new UsernameTakenException("Username already taken", "Try a different username"), HttpStatus.BAD_REQUEST);
        }  else if (userEmailTaken){
            return new ResponseEntity<>(new UsernameTakenException("Email already taken", "Try a different email"), HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userService.save(user);
        UserDto userDto = userService.toUserDto(newUser);
        userDto.setToken(jwtTokenUtil.generateToken(new UserDetailsImpl(newUser)));
        return ResponseEntity.ok(userDto);

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

    @RequestMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authRequest) throws Exception {

        authenticate(authRequest.getUsername(), authRequest.getPassword());

        Optional<User>  user = userService
                .readOneUserById(authRequest.getUsername());

        if(user.isPresent()){
            UserDto userDTO = userService.toUserDto(user.get());
            userDTO.setToken(jwtTokenUtil.generateToken(new UserDetailsImpl(user.get())));

            return ResponseEntity.ok(userDTO);
        }
        return new ResponseEntity<String>( "Invalid user", HttpStatus.BAD_REQUEST);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
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
