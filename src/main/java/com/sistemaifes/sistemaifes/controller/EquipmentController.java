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

import com.sistemaifes.sistemaifes.dto.request.EquipmentRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.EquipmentResponseDTO;
import com.sistemaifes.sistemaifes.model.Equipment;
import com.sistemaifes.sistemaifes.service.EquipmentService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public @ResponseBody List<EquipmentResponseDTO> getAll(){
        return equipmentService.getAll();
    }
    
    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Equipment saveEquipment(@RequestBody EquipmentRequestDTO data){
        return equipmentService.saveEquipment(data);
    }

    @GetMapping("/{id}")
    public Equipment findById(@PathVariable @NotNull @Positive Long id){
        return equipmentService.findById(id);
    }

    @GetMapping("/findName/{name}")
    public List<Equipment> findEquipmentByName(@PathVariable @NotNull String name){
        return equipmentService.findEquipmentByName(name);
    }

    @PutMapping("/edit/{id}")
    public Equipment update(@PathVariable @NotNull @Positive Long id, @RequestBody Equipment equipment){
        return equipmentService.update(id, equipment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        equipmentService.delete(id);
    }
    
    
}
