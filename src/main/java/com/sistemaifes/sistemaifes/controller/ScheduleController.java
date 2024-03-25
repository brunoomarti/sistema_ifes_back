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

import com.sistemaifes.sistemaifes.dto.request.ScheduleRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.ScheduleResponseDTO;
import com.sistemaifes.sistemaifes.model.Schedule;
import com.sistemaifes.sistemaifes.service.ScheduleService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/schedule")
public class ScheduleController {
    private final ScheduleService ScheduleService;

    public ScheduleController(ScheduleService ScheduleService) {
        this.ScheduleService = ScheduleService;
    }

    @GetMapping
    public @ResponseBody List<ScheduleResponseDTO> getAll(){
        return ScheduleService.getAll();
    }
    
    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Schedule saveSchedule(@RequestBody ScheduleRequestDTO data){ 
        return ScheduleService.saveSchedule(data);
    }

    @GetMapping("/{id}")
    public Schedule findById(@PathVariable @NotNull @Positive Long id){
        return ScheduleService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Schedule update(@PathVariable @NotNull @Positive Long id, @RequestBody Schedule schedule){
        return ScheduleService.update(id, schedule);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        ScheduleService.delete(id);
    }
}
