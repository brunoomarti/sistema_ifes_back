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
@Table(name = "local")
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

    @OneToMany(mappedBy = "local")
    @JsonIgnore
    private List<EquipmentLocal> equipments;

    public Local(LocalRequestDTO data){
        this.name = data.name();
        this.capacity = data.capacity();
    }
}
