package com.teamProject.ManagmentSytem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "USER_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="profile_id")
    private Profile profile;

}
