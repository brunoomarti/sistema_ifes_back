package com.sistemaifes.sistemaifes.repository;

import com.sistemaifes.sistemaifes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemaifes.sistemaifes.model.Allocation;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AllocationRepository extends JpaRepository<Allocation, Long>{

    @Query(value = """
        SELECT *
        FROM allocation a
        JOIN allocation_schedule asch ON a._id = asch.allocation_id
        WHERE asch.schedule_id = :scheduleId
        """, nativeQuery = true)
    List<Allocation> findByScheduleId(Long scheduleId);

    @Query(value = "SELECT a FROM Allocation a WHERE a.location._id = :localId")
    List<Allocation> findByLocalId(Long localId);

    @Query(value = "SELECT a FROM Allocation a WHERE a.classe._id = :classeId")
    List<Allocation> findByClasseId(Long classeId);

}
