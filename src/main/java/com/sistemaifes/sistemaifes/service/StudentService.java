package com.sistemaifes.sistemaifes.service;

import org.springframework.stereotype.Service;
 
import com.sistemaifes.sistemaifes.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }
}
