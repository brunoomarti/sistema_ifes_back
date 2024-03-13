package com.sistemaifes.sistemaifes.model;

import com.sistemaifes.sistemaifes.dto.request.DisciplineRequestDTO;
import com.sistemaifes.sistemaifes.dto.request.StudentRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discipline")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable = false)
    private String name;
    
    public Discipline(DisciplineRequestDTO data) {
        this.name = data.name();
    }
}
