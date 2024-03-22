package com.sistemaifes.sistemaifes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaifes.sistemaifes.service.CoordinationService;

@RestController
@RequestMapping("api/coordination")
public class CoordinationController {
    private CoordinationService coordinationService;

    public CoordinationController(CoordinationService coordinationService){
        this.coordinationService = coordinationService;
    }
}
