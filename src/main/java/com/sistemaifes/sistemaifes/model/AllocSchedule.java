package com.sistemaifes.sistemaifes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.AllocScheduleRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alloc_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllocSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @ManyToOne
    @JoinColumn(name = "id_schedule")
    private Schedule schedule;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_allocation")
    @JsonIgnore
    private Allocation allocation;

    public AllocSchedule(AllocScheduleRequestDTO data){
        this.schedule = data.schedule();
        this.allocation = data.allocation();
    }

}
