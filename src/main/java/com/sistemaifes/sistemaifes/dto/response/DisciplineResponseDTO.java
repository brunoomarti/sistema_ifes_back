package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Course;
import com.sistemaifes.sistemaifes.model.Discipline;

public record DisciplineResponseDTO(
    Long _id,
    String name,
    String acronym,
    Course course
){
  public DisciplineResponseDTO(Discipline discipline){
    this(discipline.get_id(), discipline.getName(), discipline.getAcronym(), discipline.getCourse());
  }  
}
