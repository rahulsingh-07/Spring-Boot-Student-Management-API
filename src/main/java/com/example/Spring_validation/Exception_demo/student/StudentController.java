package com.example.Spring_validation.Exception_demo.student;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/student") // Base URL for all endpoints in this controller
public class StudentController {

    @Autowired // Injects the StudentService bean
    private StudentService studentService;

    // GET: Fetch all student records
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudent() {
        return ResponseEntity.ok(studentService.allStudents());
    }

    // GET: Fetch student by roll number
    @GetMapping("/{rollNo}")
    public ResponseEntity<StudentResponseDTO> getStudentByRollNo(@PathVariable String rollNo) {
        return ResponseEntity.ok(studentService.student(rollNo));
    }

    // POST: Create a new student with validation on input fields
    @PostMapping
    public ResponseEntity<StudentModel> saveStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    // DELETE: Remove student by roll number
    @DeleteMapping("/{rollNo}")
    public ResponseEntity<String> deleteStudent(@PathVariable String rollNo) {
        studentService.removeStudent(rollNo);
        return ResponseEntity.ok("Student with id: " + rollNo + " deleted successfully");
    }

    // PUT: Update full student details by roll number
    @PutMapping("/{rollNo}")
    public ResponseEntity<StudentResponseDTO> editStudent(@PathVariable String rollNo, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(rollNo, studentDTO));
    }

    // PATCH: Update partial student details (e.g., only email/password)
    @PatchMapping("/{rollNo}")
    public ResponseEntity<StudentResponseDTO> editStudentPartially(
            @Valid @PathVariable String rollNo,
            @RequestBody Map<String, Object> edits) {
        return ResponseEntity.ok(studentService.updateStudentPartially(rollNo, edits));
    }
}
