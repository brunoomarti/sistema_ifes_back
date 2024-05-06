package com.sistemaifes.sistemaifes.model;
 
import com.sistemaifes.sistemaifes.dto.request.StudentScheduleRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_studentSchedule;

    private String name;
    private String _id;
    private String start_time;
    private String end_time;

    public StudentSchedule(StudentScheduleRequestDTO data){
        this.name = data.name();
        this._id = data._id();
        this.start_time = data.start_time();
        this.end_time = data.end_time();
    }
}
