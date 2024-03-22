package com.sistemaifes.sistemaifes.model;

import com.sistemaifes.sistemaifes.dto.request.CoordinatorRequestDTO;

import jakarta.persistence.Column;
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

    @Column
    private String acronym; // Sigla

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_coordinator")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Coordinator coordinator;


    public Coordination(CoordinatorRequestDTO data) {
        this.name = data.name();
    }
}
