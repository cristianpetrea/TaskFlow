package com.example.Taskflow.exception;

public class ProjectAlreadyExistsException extends RuntimeException{
    public ProjectAlreadyExistsException(String message){
        super(message);
    }
}
