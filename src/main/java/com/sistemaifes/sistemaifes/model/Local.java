package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.LocalRequestDTO;

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
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column
    private Integer capacity;

    @OneToMany(mappedBy = "location") 
    @JsonIgnore
    private List<EquipmentLocal> equipments;

    @OneToMany(mappedBy = "location") 
    @JsonIgnore
    private List<Allocation> allocations;

    @Column
    private boolean allocated;

    public Local(LocalRequestDTO data){
        this.name = data.name();
        this.capacity = data.capacity();
        this.allocated = data.allocated();
        this.equipments = data.equipments();
    }
}
