package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.DisciplineRequestDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @Column(length = 100)
    @Length(min = 3, max = 100)
    private String name;

    @Column(length = 10)
    @Length(min = 2, max = 10)
    private String acronym;

    @OneToMany(mappedBy = "discipline") 
    @JsonIgnore
    private List<Lesson> lessons; 

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;
   
    public Discipline(DisciplineRequestDTO data) {
        this.name = data.name();
        this.acronym = data.acronym();
        this.course = data.course();
    }
}
