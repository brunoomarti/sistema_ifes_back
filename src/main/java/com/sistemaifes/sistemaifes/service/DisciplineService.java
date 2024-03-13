package com.sistemaifes.sistemaifes.service;

import org.springframework.stereotype.Service;

import com.sistemaifes.sistemaifes.repository.DisciplineRepository;

@Service
public class DisciplineService {
    private DisciplineRepository repository;

    public DisciplineService(DisciplineRepository repository){
        this.repository = repository;
    }
}
