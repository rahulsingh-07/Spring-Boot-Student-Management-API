package com.example.Spring_validation.Exception_demo.exceptionHandler;

public class DuplicateStudentException extends RuntimeException{
    public DuplicateStudentException(String message){
        super(message);
    }
}
