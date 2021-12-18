package com.teamProject.ManagmentSytem.services;

import com.teamProject.ManagmentSytem.entities.Profile;
import com.teamProject.ManagmentSytem.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.Profiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

  ProfileRepository repository;

  public List<Profile> getProfiles(){
      return new ArrayList<Profile>(repository.findAll());
  }

  public Optional<Profile> getProfile(Long id){
      return repository.findById(id);
  }

  public void createProfile(Profile profile){
      repository.save(profile);
  }

  public void updateProfile(Profile profile){
      repository.save(profile);
  }

  public void deleteProfile(Long id){
      repository.deleteById(id);

  }

}
