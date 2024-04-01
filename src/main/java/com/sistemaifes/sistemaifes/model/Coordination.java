package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.sistemaifes.sistemaifes.dto.request.CoordinationRequestDTO; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    private String acronym;  

    @Column(nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "id_coordinator")
    private Coordinator coordinator;

    @OneToMany(mappedBy = "coordination")
    private List<Teacher> teacher;

    public Coordination(CoordinationRequestDTO data) {
        this.name = data.name();
        this.acronym = data.acronym();
        this.description = data.description();
    }
}
