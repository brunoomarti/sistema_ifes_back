package com.sistemaifes.sistemaifes.model;
 
import com.sistemaifes.sistemaifes.dto.request.TeacherRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; 
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 
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
public class Teacher extends User {  
    
    @Column(nullable = false)
    private String teacherCode;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String educationLevel;

    @Column(nullable = false)
    private boolean situation;

    @Column(nullable = false)
    private boolean coordinator;

    @ManyToOne
    @JoinColumn(name = "id_coordination")
    private Coordination coordination;
    
    public Teacher(TeacherRequestDTO data) { 
       this.setName(data.name());
       this.teacherCode = data.teacherCode();
       this.specialty = data.specialty();
       this.educationLevel = data.educationLevel();
       this.situation = data.situation();
       this.coordinator = data.coordinator();
       this.coordination = data.coordination();
    }
}
