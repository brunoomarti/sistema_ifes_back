package com.sistemaifes.sistemaifes.model;

import com.sistemaifes.sistemaifes.dto.request.EventRequestDTO;

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
    private String name;

    @Column
    private String description;

    @Column
    private boolean allocated;

    public Event(EventRequestDTO data){
        this.name = data.name();
        this.description = data.description();
        this.allocated = data.allocated();
    }
}
