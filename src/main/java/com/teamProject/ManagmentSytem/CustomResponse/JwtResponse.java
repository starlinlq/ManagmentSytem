package com.teamProject.ManagmentSytem.CustomResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String userName;
    private String email;
    private String token;
    private List<String> roles;
}
