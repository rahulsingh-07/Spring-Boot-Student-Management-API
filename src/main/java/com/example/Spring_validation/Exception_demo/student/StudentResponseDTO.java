package com.example.Spring_validation.Exception_demo.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
    private String name;
    private String rollNo;
    private String email;
    private Date birthdate;
    private String department;
}
