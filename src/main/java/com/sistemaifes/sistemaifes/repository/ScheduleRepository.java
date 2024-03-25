package com.sistemaifes.sistemaifes.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.sistemaifes.sistemaifes.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
}
