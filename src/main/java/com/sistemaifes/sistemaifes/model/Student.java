package com.sistemaifes.sistemaifes.model;

import com.sistemaifes.sistemaifes.dto.request.EquipmentRequestDTO;
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
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String studentCode;

    @Column 
    private double performanceCoefficient;

    @Column 
    private boolean situation;
    
    @Column
    private Integer period;
    
    public Student(StudentRequestDTO data) {
        this.name = data.name();
        this.studentCode = data.studentCode();
        this.performanceCoefficient = data.performanceCoefficient();
        this.situation = data.situation();
        this.period = data.period();
    }
}
