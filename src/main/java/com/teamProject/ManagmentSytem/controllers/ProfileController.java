package com.teamProject.ManagmentSytem.controllers;

import com.teamProject.ManagmentSytem.dto.ProfileDto;
import com.teamProject.ManagmentSytem.entities.Profile;
import com.teamProject.ManagmentSytem.exception.ProfileNotFound;
import com.teamProject.ManagmentSytem.repositories.ProfileRepository;
import com.teamProject.ManagmentSytem.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/profile")
@CrossOrigin(origins = "http://localhost:3000/")
public class ProfileController {
    @Autowired
    ProfileService service;

    @GetMapping
    public ResponseEntity<?> readAll(){
        try{
            List<Profile> profiles = service.getProfiles();
            return new ResponseEntity<>(profiles, HttpStatus.OK);
        } catch (RuntimeException err){
            return new ResponseEntity<>("Unable to find profile list",HttpStatus.OK );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleProfile(@PathVariable long id){
        Optional<ProfileDto> profile = service.getProfile(id);

        if(profile.isPresent()){
            return ResponseEntity.ok(profile.get());
        } else
            return new ResponseEntity<>(new ProfileNotFound("Incorrect profile id", "Please use a different id"), HttpStatus.BAD_REQUEST);
    }








}
