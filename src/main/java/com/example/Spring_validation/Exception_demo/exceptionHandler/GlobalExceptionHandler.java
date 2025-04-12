package com.example.Spring_validation.Exception_demo.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.StubNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice// Marks this class as a global exception handler for REST controllers
public class GlobalExceptionHandler {

    // Handles StudentFotFoundException and returns a 404 Not Found response
    @ExceptionHandler(StudentFotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentFotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handles DuplicateStudentException and returns a 409 Conflict response
    @ExceptionHandler(DuplicateStudentException.class)
    public ResponseEntity<String> handleDuplicateStudentException(DuplicateStudentException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }

    // Handles InvalidStudentDataException and returns a 400 Bad Request response
    @ExceptionHandler(InvalidStudentDataException.class)
    public ResponseEntity<String> handleInvalidStudentException(InvalidStudentDataException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    // Handles validation errors thrown by @Valid annotations in DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException e){
        Map<String,String> errors=new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
