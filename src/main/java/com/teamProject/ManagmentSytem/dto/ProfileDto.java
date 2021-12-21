package com.teamProject.ManagmentSytem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String address;
    private String state;
    private String gender;
    private String phoneNumber;
    private String emergencyNumber;
    private String department;
    private int salary;
}
