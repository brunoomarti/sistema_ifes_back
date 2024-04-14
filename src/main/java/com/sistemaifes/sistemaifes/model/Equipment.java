package com.sistemaifes.sistemaifes.model;
 
import java.util.List;

import com.sistemaifes.sistemaifes.dto.request.EquipmentRequestDTO;

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
@Table(name = "equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "equipment")
    private List<EquipmentLocal> local;

    public Equipment(EquipmentRequestDTO data) {
        this.name = data.name();
    }
    
}
