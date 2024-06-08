package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaifes.sistemaifes.model.Coordinator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT c FROM Coordinator c WHERE c.coordination._id = :coordinationId")
    List<Coordinator> findByCoordinationId(@Param("coordinationId") Long coordinationId);

    List<Coordinator> findByNameContainingIgnoreCase(String name);
    
}
