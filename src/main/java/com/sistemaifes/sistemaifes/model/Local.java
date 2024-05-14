package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.LocalRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @Column(length = 100)
    @Length(min = 3, max = 100)
    private String name;

    @Column
    @Positive
    private Integer capacity;

    @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE) 
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
