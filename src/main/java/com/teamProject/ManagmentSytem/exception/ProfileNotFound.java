package com.teamProject.ManagmentSytem.exception;

public class ProfileNotFound extends CustomException{
    public ProfileNotFound(String msg, String nextStep){
        super(msg, nextStep);
    }
}
