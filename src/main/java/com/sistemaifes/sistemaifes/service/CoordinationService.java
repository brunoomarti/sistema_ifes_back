package com.sistemaifes.sistemaifes.service;

import org.springframework.stereotype.Service;

import com.sistemaifes.sistemaifes.repository.CoordinationRepository;

@Service
public class CoordinationService {
    private CoordinationRepository repository;

    public CoordinationService(CoordinationRepository repository){
        this.repository = repository;
    }
}
