package com.sistemaifes.sistemaifes.model;
 
import com.sistemaifes.sistemaifes.dto.request.TeacherRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String teacherCode;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String academic_degree;

    @Column(nullable = false)
    private boolean situation;

    @OneToOne(mappedBy = "teacher")
    private Coordinator coordinator;
    
    public Teacher(TeacherRequestDTO data) {
       this.name = data.name();
       this.teacherCode = data.teacherCode();
       this.specialty = data.specialty();
       this.academic_degree = data.academic_degree();
       this.situation = data.situation();
       this.coordinator = data.coordinator();
    }
}
