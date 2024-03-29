package com.sistemaifes.sistemaifes.model;

import java.util.Date; 
import com.sistemaifes.sistemaifes.dto.request.ScheduleRequestDTO;

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
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    public Schedule(ScheduleRequestDTO data){
        this.startTime = data.startTime();
        this.endTime = data.endTime();
    }
}
