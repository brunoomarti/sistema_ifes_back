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

import com.sistemaifes.sistemaifes.dto.request.AllocationRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.AllocationResponseDTO;
import com.sistemaifes.sistemaifes.model.Allocation;
import com.sistemaifes.sistemaifes.service.AllocationService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/allocate")
public class AllocationController {
    
    private AllocationService allocationService;

    public AllocationController(AllocationService allocationService){
        this.allocationService = allocationService;
    }

    @GetMapping
    public @ResponseBody List<AllocationResponseDTO> getAll(){
        return allocationService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Allocation saveAllocation(@RequestBody AllocationRequestDTO data){
        return allocationService.saveAllocation(data);
    }

    @GetMapping("/{id}")
    public Allocation findById(@PathVariable @NotNull @Positive Long id){
        return allocationService.findById(id);
    }

    @PutMapping("/{id}")
    public Allocation update(@PathVariable @NotNull @Positive Long id, @RequestBody Allocation allocation){
        return allocationService.update(id, allocation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        allocationService.delete(id);
    }

}
