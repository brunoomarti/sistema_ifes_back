package com.sistemaifes.sistemaifes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaifes.sistemaifes.dto.request.CoordinatorRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.CoordinatorResponseDTO; 
import com.sistemaifes.sistemaifes.model.Coordinator; 
import com.sistemaifes.sistemaifes.service.CoordinatorService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/coordinator")
public class CoordinatorController {
    private final CoordinatorService coordinatorService; 

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @GetMapping
    public @ResponseBody List<CoordinatorResponseDTO> getAll(){
        return coordinatorService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Coordinator saveCoordinator(@RequestBody CoordinatorRequestDTO data){
        return coordinatorService.saveCoordinator(data);
    }

    @GetMapping("/{id}")
    public Coordinator findById(@PathVariable @NotNull @Positive Long id){
        return coordinatorService.findById(id);
    }

    @GetMapping("/findName/{name}")
    public List<Coordinator> findCoordinatorByName(@PathVariable @NotNull String name){
        return coordinatorService.findCoordinatorByName(name);
    }

    @PutMapping("/{id}")
    public Coordinator update(@PathVariable @NotNull @Positive Long id, @RequestBody Coordinator coordinator){
        return coordinatorService.update(id, coordinator);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        coordinatorService.delete(id);
    }

}
