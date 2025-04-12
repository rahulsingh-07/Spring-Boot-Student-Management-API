package com.example.Spring_validation.Exception_demo.student;

import jakarta.validation.constraints.*; // Validation annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // Lombok: generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok: generates a no-argument constructor
@AllArgsConstructor // Lombok: generates a constructor with all arguments
public class StudentDTO {

    // Name must not be blank
    @NotBlank(message = "Name is required")
    private String name;

    // Roll number must not be blank
    @NotBlank(message = "RollNo is required")
    private String rollNo;

    // Password must be at least 6 characters long and not blank
    @NotBlank(message = "Password is required")
    @Size(min=6, message = "Password length must greater than 5")
    private String password;

    // Email must be a valid format and not blank
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    // Birthdate cannot be null
    @NotNull(message = "Birthdate is required")
    private Date birthdate;

    // Department must not be blank or null
    @NotNull(message = "Department is required")
    @NotBlank(message = "Department is required")
    private String department;

    // CGPA must not be null and should be less than or equal to 10
    @NotNull(message = "cgpa is required")
    @Max(value = 10, message = "cgpa must not greater than 10")
    private int cgpa;

    // Aadhaar number must be at least 12 characters and not blank
    @NotBlank(message = "AadhaarNumber is required")
    @Size(min = 12, message = "AadhaarNumber length not valid")
    private String aadhaarNumber;
}
