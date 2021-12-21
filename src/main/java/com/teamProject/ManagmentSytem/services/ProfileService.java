package com.teamProject.ManagmentSytem.services;

import com.teamProject.ManagmentSytem.dto.ProfileDto;
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
    @Autowired
    ProfileRepository repository;

  public List<Profile> getProfiles(){
      return new ArrayList<Profile>(repository.findAll());
  }

  public Optional<ProfileDto> getProfile(Long id){
      Optional<Profile> profile =repository.findById(id);
      if(profile.isEmpty()){
          return Optional.empty();
      } else
          return Optional.of(ProfileMapper.toProfileDto(profile.get()));
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


  public static class ProfileMapper{

      public static ProfileDto toProfileDto(Profile profile){
          return ProfileDto.builder()
                  .firstName(profile.getFirstName())
                  .lastName(profile.getLastName())
                  .email(profile.getEmail())
                  .dateOfBirth(profile.getDateOfBirth())
                  .state(profile.getState())
                  .address(profile.getAddress())
                  .phoneNumber(profile.getPhoneNumber())
                  .emergencyNumber(profile.getEmergencyNumber())
                  .gender(profile.getGender())
                  .department(profile.getDepartment())
                  .salary(profile.getSalary()).build();
      }

  }

}
