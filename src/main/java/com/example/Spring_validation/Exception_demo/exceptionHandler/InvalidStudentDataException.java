package com.example.Spring_validation.Exception_demo.exceptionHandler;

public class InvalidStudentDataException extends RuntimeException{
    public InvalidStudentDataException(String message){
        super(message);
    }
}
