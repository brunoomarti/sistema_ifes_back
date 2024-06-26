package com.sistemaifes.sistemaifes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.EquipmentLocalRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "local_equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentLocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @ManyToOne
    @JoinColumn(name = "id_equipment")
    private Equipment equipment;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_location")
    @JsonIgnore
    private Local location;
    
    @Column
    @Positive
    private Integer quantity;

    public EquipmentLocal(EquipmentLocalRequestDTO data){
        this.equipment = data.equipment();
        this.location = data.location();
        this.quantity = data.quantity();
    }
}
