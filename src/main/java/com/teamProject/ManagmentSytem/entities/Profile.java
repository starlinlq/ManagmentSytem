package com.teamProject.ManagmentSytem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Date;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue
    private Long id;
//    User information
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String department;
    private String address;
    private String gender;
    private String emergencyNumber;
    private String phoneNumber;
    private String state;
    private int salary = 0;


    // Default values as these may not apply to all users.
    private int overtime = 0;
    private int medical = 0;
    private int bonus = 0;
    private int other = 0;
//    Overtime
    private double totalOvertime = 0;
    private double ratePerHour = 0;

    // Relations
    @JsonIgnore
    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private User user;


}
