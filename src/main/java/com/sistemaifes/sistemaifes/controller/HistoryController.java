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

import com.sistemaifes.sistemaifes.dto.request.HistoryRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.HistoryResponseDTO;
import com.sistemaifes.sistemaifes.model.History;
import com.sistemaifes.sistemaifes.service.HistoryService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/history")
public class HistoryController {

    private HistoryService historyService;

    public HistoryController(HistoryService historyService){
        this.historyService = historyService;
    }

    @GetMapping
    public @ResponseBody List<HistoryResponseDTO> getAll(){
        return historyService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public History saveHistory(@RequestBody HistoryRequestDTO data){
        return historyService.saveHistory(data);
    }

    @GetMapping("/{id}")
    public History findById(@PathVariable @NotNull @Positive Long id){
        return historyService.findById(id);
    }

    @PutMapping("/{id}")
    public History update(@PathVariable @NotNull @Positive Long id, @RequestBody History history){
        return historyService.update(id, history);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        historyService.delete(id);
    }
}
