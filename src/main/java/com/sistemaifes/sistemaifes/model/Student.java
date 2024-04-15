package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.sistemaifes.sistemaifes.dto.request.StudentRequestDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {

    @Column(nullable = false)
    private String studentCode;
    
    @ManyToMany(mappedBy = "students")
    private List<Lesson> lessons;

    public Student(StudentRequestDTO data) {
        this.setName(data.name());
        this.studentCode = data.studentCode();
    }
}
