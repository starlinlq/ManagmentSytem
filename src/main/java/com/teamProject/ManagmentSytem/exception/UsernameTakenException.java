package com.teamProject.ManagmentSytem.exception;

public class UsernameTakenException extends CustomException{
    public UsernameTakenException(String msg, String nextStep){
        super(msg, nextStep);
    }
}
