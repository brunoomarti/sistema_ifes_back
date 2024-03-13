package com.sistemaifes.sistemaifes.model;


import com.sistemaifes.sistemaifes.dto.request.CoordinatorRequestDTO;
import com.sistemaifes.sistemaifes.dto.request.DisciplineRequestDTO;

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
@Table(name = "coordinator")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shift;

    public Coordinator(CoordinatorRequestDTO data) {
        this.name = data.name();
        this.shift = data.shift();
    }
}
