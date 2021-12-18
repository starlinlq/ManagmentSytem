package com.teamProject.ManagmentSytem.controllers;

import com.teamProject.ManagmentSytem.entities.Profile;
import com.teamProject.ManagmentSytem.repositories.ProfileRepository;
import com.teamProject.ManagmentSytem.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    ProfileService service;

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome(Authentication auth){
            UserDetails user = (UserDetails) auth.getPrincipal();
        return ResponseEntity.ok("Welcome " + user.getUsername());
    }

    // http://localhost:8080/api/v1/profile
    @GetMapping
    public ResponseEntity<?> readAll(){
        try{
            List<Profile> profiles = service.getProfiles();
            return new ResponseEntity<>(profiles, HttpStatus.OK);
        } catch (RuntimeException err){
            return new ResponseEntity<>("Unable to find profile list",HttpStatus.OK );
        }
    }

    // http:localhost:8080/api/v1/profile
    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody Profile profile){
        try{
            service.createProfile(profile);
            return new ResponseEntity<>(profile,HttpStatus.OK);
        } catch (RuntimeException err) {
            return new ResponseEntity<>("Could not add item ", HttpStatus.BAD_REQUEST);
        }
    }



}
