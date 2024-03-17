package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaifes.sistemaifes.model.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long>
{
    boolean existsByNameIgnoreCase(String name);
    
    List<Classe> findByNameContainingIgnoreCase(String name);
}
