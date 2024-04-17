package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.EventRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.EventResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Event;
import com.sistemaifes.sistemaifes.repository.EventRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository){
        this.repository = repository;
    }

    public List<EventResponseDTO> getAll() {
        return repository.findAll().stream().map((EventResponseDTO::new)).toList();
    }

    public Event findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Event saveEvent(EventRequestDTO data){
        Event localData = new Event(data);
        return repository.save(localData);
    }

    public Event update(@NotNull @Positive Long id, @Valid Event event){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(event.getName());
                    recordFound.setDescription(event.getDescription());
                    recordFound.setAllocated(event.isAllocated());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
