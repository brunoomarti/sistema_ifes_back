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

import com.sistemaifes.sistemaifes.dto.request.CoordinationRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.CoordinationResponseDTO;
import com.sistemaifes.sistemaifes.model.Coordination;
import com.sistemaifes.sistemaifes.service.CoordinationService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/coordination")
public class CoordinationController {
    private CoordinationService coordinationService;

    public CoordinationController(CoordinationService coordinationService){
        this.coordinationService = coordinationService;
    }

    @GetMapping
    public @ResponseBody List<CoordinationResponseDTO> getAll(){
        return coordinationService.getAll();
    }

    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Coordination saveCoordination(@RequestBody CoordinationRequestDTO data){
        return coordinationService.saveCoordination(data);
    }

    @GetMapping("/{id}")
    public Coordination findById(@PathVariable @NotNull @Positive Long id){
        return coordinationService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Coordination update(@PathVariable @NotNull @Positive Long id, @RequestBody Coordination coordination){
        return coordinationService.update(id, coordination);
    }

    @GetMapping("/{id}/records")
    public List<Object> getCoordinationRecords(@PathVariable @NotNull @Positive Long id) {
        return coordinationService.getRecordsCoordination(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        coordinationService.delete(id);
    }


}
