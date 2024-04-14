package com.sistemaifes.sistemaifes.model;

import java.time.LocalDate;
import java.util.Date;

import com.sistemaifes.sistemaifes.dto.request.EventRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column
    private String description;

    @Column
    private LocalDate eventDate;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    public Event(EventRequestDTO data){
        this.description = data.description();
        this.eventDate = data.eventDate();
        this.startTime = data.startTime();
        this.endTime = data.endTime();
    }
}
