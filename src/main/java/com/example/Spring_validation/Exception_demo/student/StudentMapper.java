package com.example.Spring_validation.Exception_demo.student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// This interface is a MapStruct mapper that automatically maps between DTOs and entities.
// The annotation tells Spring to treat this as a component (bean) for dependency injection.
@Mapper(componentModel = "spring")
public interface StudentMapper {

    // Converts StudentDTO to StudentModel (Entity)
    StudentModel toEntity(StudentDTO studentDTO);

    // Converts StudentModel (Entity) to StudentResponseDTO
    StudentResponseDTO toDTO(StudentModel studentModel);

    // Converts a list of StudentModel objects to a list of StudentResponseDTOs
    List<StudentResponseDTO> toDTOList(List<StudentModel> studentModels);
}
