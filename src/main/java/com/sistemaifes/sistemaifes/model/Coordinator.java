package com.sistemaifes.sistemaifes.model;
 

import com.sistemaifes.sistemaifes.dto.request.CoordinatorRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
public class Coordinator extends User { 

    @OneToOne(mappedBy = "coordinator")
    private Coordination coordination;

    public Coordinator(CoordinatorRequestDTO data) {
        this.setName(data.name());
        this.setLogin(data.login());
        this.setPassword(data.password());
        this.coordination = data.coordination(); 
    }
}
