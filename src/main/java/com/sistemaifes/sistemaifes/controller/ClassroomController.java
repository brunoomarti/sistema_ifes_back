package com.sistemaifes.sistemaifes.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaifes.sistemaifes.service.ClassroomService;

@RestController
@RequestMapping("api/classroom")
public class ClassroomController {
    private ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService){
        this.classroomService = classroomService;
    }
}
