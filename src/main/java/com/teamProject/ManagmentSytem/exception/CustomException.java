package com.teamProject.ManagmentSytem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException {
    private String msg;
    private String nextStep;
}

