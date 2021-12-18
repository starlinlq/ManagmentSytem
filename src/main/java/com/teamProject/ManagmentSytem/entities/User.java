package com.teamProject.ManagmentSytem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_TBL")
@Data
public class User {
    @JsonIgnore
    @Id
    @GeneratedValue
    private long id;
    @Pattern(regexp = "[a-zA-Z]{5,}")
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @Size(min = 8)
    @NotBlank
    private String password;
    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> roles = new ArrayList<>();

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="profile_id")
    private Profile profile;

}
