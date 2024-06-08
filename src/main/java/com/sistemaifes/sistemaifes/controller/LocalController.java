package com.sistemaifes.sistemaifes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sistemaifes.sistemaifes.dto.request.LocalRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.LocalResponseDTO;
import com.sistemaifes.sistemaifes.model.Local;
import com.sistemaifes.sistemaifes.service.LocalService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/local")
public class LocalController {
    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping
    public @ResponseBody List<LocalResponseDTO> getAll(){
        return localService.getAll();
    }
    
    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Local saveLocal(@RequestBody LocalRequestDTO data){ 
        return localService.saveLocal(data);
    }

    @GetMapping("/{id}")
    public Local findById(@PathVariable @NotNull @Positive Long id){
        return localService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Local update(@PathVariable @NotNull @Positive Long id, @RequestBody Local local){
        return localService.update(id, local);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        localService.delete(id);
    }


    @GetMapping("/{id}/records")
    public List<Object> getAllocationRecords(@PathVariable @NotNull @Positive Long id) {
        return localService.getRecordsAllocation(id);
    }

    @GetMapping("/availableLocal")
    public @ResponseBody List<Local> getAllClientsActiveAndDebit() {
        return localService.getAllUnallocatedLocationst();
    }
}
