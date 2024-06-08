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

import com.sistemaifes.sistemaifes.dto.request.ClasseRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.ClasseResponseDTO;
import com.sistemaifes.sistemaifes.model.Classe;
import com.sistemaifes.sistemaifes.service.ClasseService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/classe")
public class ClasseController {

    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping
    public @ResponseBody List<ClasseResponseDTO> getAll(){
        return classeService.getAll();
    }

    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Classe save(@RequestBody ClasseRequestDTO data){
        return classeService.save(data);
    }
    
    @GetMapping("/{id}")
    public Classe findById(@PathVariable @NotNull @Positive Long id){
        return classeService.findById(id);
    }

    @GetMapping("/findName/{name}")
    public List<Classe> findEquipmentByName(@PathVariable @NotNull String name){
        return classeService.findEquipmentByName(name);
    }

    @PutMapping("/edit/{id}")
    public Classe update(@PathVariable @NotNull @Positive Long id, @RequestBody Classe classe){
        return classeService.update(id, classe);
    }

    @GetMapping("/{id}/records")
    public List<Object> getRecordsDiscipline(@PathVariable @NotNull @Positive Long id) {
        return classeService.getRecordsClasse(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        classeService.delete(id);
    }
}
