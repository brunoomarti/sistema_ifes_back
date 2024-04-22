package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemaifes.sistemaifes.model.History;

public interface HistoryRepository extends JpaRepository<History, Long>{
    
}
