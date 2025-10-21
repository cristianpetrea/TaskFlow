package com.example.Taskflow.exception;

public class InvalidTaskException extends RuntimeException{

    public InvalidTaskException(String message){
        super(message);
    }
}
