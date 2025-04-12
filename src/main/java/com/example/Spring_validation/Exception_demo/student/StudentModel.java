package com.example.Spring_validation.Exception_demo.student;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true,name="rollno")
    private String rollNo;

    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private Date birthdate;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int cgpa;

    @Column(nullable = false,unique = true,name="aadhaar_number")
    private String aadhaarNumber;
}
