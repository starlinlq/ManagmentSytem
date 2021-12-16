package com.teamProject.ManagmentSytem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class ProfileController {

    @GetMapping
    public ResponseEntity<?> welcome(Authentication auth){
        UserDetails user = (UserDetails) auth.getPrincipal();
        return ResponseEntity.ok("Welcome " + user.getUsername());
    }
}
