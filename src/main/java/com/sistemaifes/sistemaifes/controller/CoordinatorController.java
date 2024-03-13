package com.sistemaifes.sistemaifes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaifes.sistemaifes.service.CoordinatorService;

@RestController
@RequestMapping("api/coordinator")
public class CoordinatorController {
    private final CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

}
