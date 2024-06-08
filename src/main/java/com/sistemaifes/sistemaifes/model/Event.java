package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.EventRequestDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @Column(length = 100)
    @Length(min = 3, max = 10)
    private String name;

    @Column(length = 100)
    @Length(min = 3, max = 100)
    private String applicant;

    @Column
    @Length(min = 3, max = 200)
    private String description;

    @Column
    private boolean allocated;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Allocation> allocations;

    public Event(EventRequestDTO data){
        this.name = data.name();
        this.applicant = data.applicant();
        this.description = data.description();
        this.allocated = data.allocated();
    }
}
