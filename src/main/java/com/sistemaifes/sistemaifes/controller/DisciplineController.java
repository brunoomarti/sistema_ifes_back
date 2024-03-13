package com.sistemaifes.sistemaifes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaifes.sistemaifes.service.DisciplineService;

@RestController
@RequestMapping("api/discipline")
public class DisciplineController {
    private DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService){
        this.disciplineService = disciplineService;
    }
}
