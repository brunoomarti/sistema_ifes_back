package com.sistemaifes.sistemaifes.service;

import org.springframework.stereotype.Service;

import com.sistemaifes.sistemaifes.repository.ClassroomRepository;

@Service
public class ClassroomService {
    private ClassroomRepository repository;

    public ClassroomService(ClassroomRepository repository){
        this.repository = repository;
    }
}
