package com.sistemaifes.sistemaifes.dto.response;

import com.sistemaifes.sistemaifes.model.Discipline;

public record DisciplineResponseDTO(
    Long _id,
    String name
){
  public DisciplineResponseDTO(Discipline discipline){
    this(discipline.get_id(), discipline.getName());
  }  
}
