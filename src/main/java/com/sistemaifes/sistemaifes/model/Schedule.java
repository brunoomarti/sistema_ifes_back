package com.sistemaifes.sistemaifes.model;

 
import java.util.List;

import com.sistemaifes.sistemaifes.dto.request.ScheduleRequestDTO;

import jakarta.persistence.*;
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

    @Column(length = 100, nullable = false)
    private String startTime;

    @Column(length = 100, nullable = false)
    private String endTime;

    @ManyToMany
    private List<Allocation> allocation;

    public Schedule(ScheduleRequestDTO data){
        this.startTime = data.startTime();
        this.endTime = data.endTime();
    }
}
