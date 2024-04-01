package com.sistemaifes.sistemaifes.model;

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
 
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean estahAtivo;

    public User(UserRequestDTO data){
        this.name = data.name();
        this.estahAtivo = data.estahAtivo();
    }
 
}
