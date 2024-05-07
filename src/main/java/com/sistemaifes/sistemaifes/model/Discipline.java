package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.DisciplineRequestDTO;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "discipline") 
    @JsonIgnore
    private List<Lesson> lessons; 

    @ManyToOne
    @JoinColumn(name = "id_course")
    private Course course;
   
    public Discipline(DisciplineRequestDTO data) {
        this.name = data.name();
        this.course = data.course();
    }
}
