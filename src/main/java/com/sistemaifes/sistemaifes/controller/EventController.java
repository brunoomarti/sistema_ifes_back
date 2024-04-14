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

import com.sistemaifes.sistemaifes.dto.request.EventRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.EventResponseDTO; 
import com.sistemaifes.sistemaifes.model.Event;
import com.sistemaifes.sistemaifes.service.EventService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public @ResponseBody List<EventResponseDTO> getAll(){
        return eventService.getAll();
    }
    
    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Event saveEvent(@RequestBody EventRequestDTO data){ 
        return eventService.saveEvent(data);
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable @NotNull @Positive Long id){
        return eventService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Event update(@PathVariable @NotNull @Positive Long id, @RequestBody Event event){
        return eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        eventService.delete(id);
    }
}
