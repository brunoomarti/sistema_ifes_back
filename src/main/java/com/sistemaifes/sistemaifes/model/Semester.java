package com.sistemaifes.sistemaifes.model;

import java.util.Date;

import com.sistemaifes.sistemaifes.dto.request.SemesterRequestDTO;

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
@Table(name = "semester")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column
    private String semester;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    public Semester(SemesterRequestDTO data){
        this.semester = data.semester();
        this.startDate = data.startDate();
        this.endDate = data.endDate();
    }
    
}
