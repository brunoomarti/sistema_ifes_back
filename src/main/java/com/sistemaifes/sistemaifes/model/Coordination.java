package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.CoordinationRequestDTO; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 
import jakarta.persistence.OneToMany; 
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

    @Column(length = 100)
    @Length(min = 3, max = 100)
    private String name;

    @Column(length = 10)
    @Length(min = 1, max = 10)
    private String acronym;  

    @Column
    @Length(min = 3, max = 255)
    private String description; 
    
    @OneToMany(mappedBy = "coordination") 
    @JsonIgnore
    private List<Teacher> teacher;

    public Coordination(CoordinationRequestDTO data) {
        this.name = data.name();
        this.acronym = data.acronym();
        this.description = data.description();
    }
    
}
