package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaifes.sistemaifes.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
    
}
