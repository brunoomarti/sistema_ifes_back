package com.sistemaifes.sistemaifes.model;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import com.sistemaifes.sistemaifes.dto.request.UserRequestDTO;

import jakarta.persistence.*; 
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_ifes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
 
    @Column(length = 100, nullable = false)
    @Length(min = 3, max = 100)
    private String name;

    @Column(nullable = false)
    private boolean estahAtivo;

    public User(UserRequestDTO data){
        this.name = data.name();
        this.estahAtivo = data.estahAtivo();
    }
 
}
