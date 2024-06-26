package com.sistemaifes.sistemaifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.sistemaifes.sistemaifes.dto.request.HistoryRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.HistoryResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.History;
import com.sistemaifes.sistemaifes.repository.HistoryRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class HistoryService {
    
    private HistoryRepository repository;

    public HistoryService(HistoryRepository repository){
        this.repository = repository;
    }

    public List<HistoryResponseDTO> getAll() {
        return repository.findAll().stream().map((HistoryResponseDTO::new)).toList();
    }

    public History findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public History saveHistory(HistoryRequestDTO data){
        History cData = new History(data);
        return repository.save(cData);
    }
  
    public History update(@NotNull @Positive Long id, @Valid History history){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setDate(history.getDate());
                    recordFound.setAuthorName(history.getAuthorName());
                    recordFound.setChangeType(history.getChangeType());
                    recordFound.setStartDate(history.getStartDate());
                    recordFound.setEndDate(history.getEndDate());
                    recordFound.setStartTime(history.getStartTime());
                    recordFound.setEndTime(history.getEndTime());
                    recordFound.setWeekDay(history.getWeekDay());
                    recordFound.setType(history.getType());
                    recordFound.setApplicant(history.getApplicant());
                    recordFound.setLesson(history.getLesson());
                    recordFound.setEvent(history.getEvent());
                    recordFound.setLocation(history.getLocation());
                    recordFound.setClasse(history.getClasse());
                    recordFound.setSelectedTimes(history.getSelectedTimes());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

}
