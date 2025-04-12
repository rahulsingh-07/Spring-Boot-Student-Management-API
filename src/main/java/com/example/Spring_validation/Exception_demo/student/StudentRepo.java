package com.example.Spring_validation.Exception_demo.student;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<StudentModel, Integer> {

    // Find a student by their roll number (used to check or fetch a specific student)
    Optional<StudentModel> findByRollNo(String rollNo);

    // Check if a student with the given email already exists in the database
    boolean existsByEmail(String email);

    // Check if a student with the given roll number exists
    boolean existsByRollNo(String rollNo);

    // Check if a student with the given Aadhaar number exists
    boolean existsByAadhaarNumber(String aadhaarNumber);

    /**
     * Delete a student from the database using their roll number.
     *
     * @Modifying - Indicates that this is a modifying query (not a SELECT).
     * @Transactional - Ensures the operation runs within a transaction context.
     *
     * Custom JPQL delete query to remove the student with the given roll number.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM StudentModel s WHERE s.rollNo = :rollNo")
    void deleteByRollNo(@Param("rollNo") String rollNo);

}
