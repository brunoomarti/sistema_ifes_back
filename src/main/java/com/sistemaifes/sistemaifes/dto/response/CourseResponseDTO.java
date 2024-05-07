package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Course; 

public record CourseResponseDTO(
    Long _id,
    String name
){
  public CourseResponseDTO(Course course){
    this(
        course.get_id(), 
        course.getName()
    );
  }  
}
