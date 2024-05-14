package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistemaifes.sistemaifes.model.Local;

public interface LocalRepository extends JpaRepository<Local, Long> {
    @Query("SELECT l FROM Local l WHERE l.allocated = false")
    List<Local> findAllUnallocatedLocations();

    boolean existsByNameIgnoreCase(String name);
}
