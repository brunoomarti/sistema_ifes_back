package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.CourseRequestDTO; 

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "course") 
    @JsonIgnore
    private List<Discipline> disciplines;

    @OneToMany(mappedBy = "course") 
    @JsonIgnore
    private List<Student> students;

     public Course(CourseRequestDTO data) {
        this.name = data.name();
    }
}
