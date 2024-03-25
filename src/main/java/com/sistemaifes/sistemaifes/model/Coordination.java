package com.sistemaifes.sistemaifes.model;

import com.sistemaifes.sistemaifes.dto.request.CoordinationRequestDTO; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coordination")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String acronym; // Sigla

    @Column(nullable = false)
    private String description;

    @OneToOne
    private Coordinator coordinator;

    public Coordination(CoordinationRequestDTO data) {
        this.name = data.name();
        this.acronym = data.acronym();
        this.description = data.description();
    }
}
