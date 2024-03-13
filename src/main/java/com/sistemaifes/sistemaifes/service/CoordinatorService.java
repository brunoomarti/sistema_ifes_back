package com.sistemaifes.sistemaifes.service;

import org.springframework.stereotype.Service;

import com.sistemaifes.sistemaifes.repository.CoordinatorRepository;

@Service
public class CoordinatorService {
    private final CoordinatorRepository repository;

    public CoordinatorService(CoordinatorRepository repository){
        this.repository = repository;
    }
}
