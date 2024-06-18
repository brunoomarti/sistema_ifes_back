package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.CourseRequestDTO; 

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @NotNull
    @Column(length = 100)
    @Length(min = 3, max = 100)
    private String name;

    @NotNull
    @Column
    @Length(min = 4, max = 4)
    private String identityNumber;

    @OneToMany(mappedBy = "course") 
    @JsonIgnore
    private List<Discipline> disciplines;

    @OneToMany(mappedBy = "course") 
    @JsonIgnore
    private List<Student> students;

     public Course(CourseRequestDTO data) {
        this.name = data.name();
        this.identityNumber = data.identityNumber();
    }
}
