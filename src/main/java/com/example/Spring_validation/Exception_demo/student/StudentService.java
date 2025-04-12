package com.example.Spring_validation.Exception_demo.student;

import com.example.Spring_validation.Exception_demo.exceptionHandler.DuplicateStudentException;
import com.example.Spring_validation.Exception_demo.exceptionHandler.InvalidStudentDataException;
import com.example.Spring_validation.Exception_demo.exceptionHandler.StudentFotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StudentMapper studentMapper;

    // Fetch and return a list of all students as DTOs
    public List<StudentResponseDTO> allStudents(){
        List<StudentModel> list=studentRepo.findAll();
        return studentMapper.toDTOList(list);
    }

    // Fetch a single student by roll number and return as DTO
    public StudentResponseDTO student(String rollNo){
        return studentRepo.findByRollNo(rollNo)
                .map(studentMapper::toDTO)
                .orElseThrow(()->new StudentFotFoundException("Student not found with roll number:"+rollNo));

    }

    // Create and save a new student, while checking for duplicate roll no, email, or Aadhaar
    public StudentModel createStudent(StudentDTO studentDTO){
        if(studentRepo.existsByRollNo(studentDTO.getRollNo())){
            throw new DuplicateStudentException("Roll number is already exist");
        }
        if(studentRepo.existsByEmail(studentDTO.getEmail())){
            throw new DuplicateStudentException("Email already exist");
        }
        if(studentRepo.existsByAadhaarNumber(studentDTO.getAadhaarNumber())){
            throw new DuplicateStudentException("Aadhaar number is already exist");
        }
        return studentRepo.save(studentMapper.toEntity(studentDTO));
    }

    //update student details
    public StudentResponseDTO updateStudent(String rollNo, StudentDTO updatedStudentDTO){
        StudentModel existingStudent=studentRepo.findByRollNo(rollNo)
                .orElseThrow(()->new StudentFotFoundException("Student not found with roll number: "+rollNo));
        // Null checks for required fields
        if (updatedStudentDTO.getEmail() == null || updatedStudentDTO.getEmail().isEmpty()) {
            throw new InvalidStudentDataException("Email cannot be null or empty.");
        }
        if (updatedStudentDTO.getPassword() == null || updatedStudentDTO.getPassword().isEmpty()|| updatedStudentDTO.getPassword().length()<6) {
            throw new InvalidStudentDataException("Password cannot be null or empty and length must be greater than 6.");
        }
        if (!existingStudent.getEmail().equals(updatedStudentDTO.getEmail()) &&
                studentRepo.existsByEmail(updatedStudentDTO.getEmail())) {
            throw new DuplicateStudentException("Email already in use.");
        }
        existingStudent.setPassword(updatedStudentDTO.getPassword());
        existingStudent.setEmail(updatedStudentDTO.getEmail());
        StudentModel saved=studentRepo.save(existingStudent);
        return studentMapper.toDTO(saved);
    }

    // Partial update of student - only update fields that are present in the map
    public StudentResponseDTO updateStudentPartially(String rollNo, Map<String,Object> updates){
        StudentModel existingStudent=studentRepo.findByRollNo(rollNo)
                .orElseThrow(()->new StudentFotFoundException("Student not found by roll number: "+rollNo));
        if(updates.containsKey("email")){
            String email = (String) updates.get("email");
            if (email==null ||email.isBlank() ) {
                throw new InvalidStudentDataException("Email cannot be null or empty.");
            }
            existingStudent.setEmail((String) updates.get("email"));
        }
        if(updates.containsKey("password")){
            String pwd=(String)updates.get("password");
            if (pwd.length()<6||pwd.isBlank()) {
                throw new InvalidStudentDataException("password must be greater than 5 and not empty.");
            }
            existingStudent.setPassword((String)updates.get("password"));
        }
        StudentModel updatedStudent=studentRepo.save(existingStudent);
        return studentMapper.toDTO(updatedStudent);
    }

    //removing student from database
    public void removeStudent(String rollNo){
        if(!studentRepo.existsByRollNo(rollNo)){
            throw new StudentFotFoundException("Student not found by roll number: "+rollNo);
        }
        studentRepo.deleteByRollNo(rollNo);
    }

}
