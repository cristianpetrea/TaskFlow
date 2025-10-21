package com.example.Taskflow.config;

import com.example.Taskflow.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex){

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ux){

        return new ResponseEntity<>(ux.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleProjectAlreadyExists(ProjectAlreadyExistsException pe){
        return new ResponseEntity<>(pe.getMessage(),HttpStatus.CONFLICT);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleProjectNotFound(ProjectNotFoundException pe){
        return new ResponseEntity<>(pe.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNoAccess(NoAccesException ne){
        return new ResponseEntity<>(ne.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidTask(InvalidTaskException it){
        return new ResponseEntity<>(it.getMessage(),HttpStatus.CONFLICT);
    }
}
