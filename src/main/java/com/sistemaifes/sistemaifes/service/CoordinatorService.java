package com.sistemaifes.sistemaifes.service;

import java.util.List;

import com.sistemaifes.sistemaifes.controller.AuthenticationController;
import com.sistemaifes.sistemaifes.exception.InvalidLengthException;
import com.sistemaifes.sistemaifes.model.Teacher;
import com.sistemaifes.sistemaifes.repository.UserRepository;
import com.sistemaifes.sistemaifes.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sistemaifes.sistemaifes.dto.request.CoordinatorRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.CoordinatorResponseDTO;
import com.sistemaifes.sistemaifes.exception.ItemAlreadyRegisteredException;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException;
import com.sistemaifes.sistemaifes.model.Coordinator;
import com.sistemaifes.sistemaifes.repository.CoordinatorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class CoordinatorService {
    private final CoordinatorRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationController authenticationController;

    public CoordinatorService(CoordinatorRepository repository){
        this.repository = repository;
    }

    public List<CoordinatorResponseDTO> getAll() {
        return repository.findAll().stream().map((CoordinatorResponseDTO::new)).toList();
    }

    public Coordinator findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Coordinator saveCoordinator(CoordinatorRequestDTO data){
        Coordinator cordData = new Coordinator(data);

        String fullName = cordData.getName().toLowerCase();
        String firstName = fullName.split(" ")[0];

        cordData.setLogin(firstName);
        cordData.setPassword("123456!");
        cordData.setEstahAtivo(true);
        cordData.setRole(UserRole.COORDINATOR);

        registerCoordinator(cordData);

        return cordData;
    }

    public ResponseEntity registerCoordinator(Coordinator c){

        if(this.userRepository.findByLogin(c.getLogin()) != null) return ResponseEntity.badRequest().build();

        if (!authenticationController.validate(c.getPassword())) {
            return ResponseEntity.badRequest().body("A senha deve conter pelo menos um caractere especial e seis caracteres alfanuméricos.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(c.getPassword());

        c.setEstahAtivo(true);
        c.setPassword(encryptedPassword);

        this.repository.save(c);

        return ResponseEntity.ok().build();
    }

    public boolean verifyIfCoordinatorExist(String equipmentName) {
        return repository.existsByNameIgnoreCase(equipmentName);
    }

    public Coordinator update(@NotNull @Positive Long id, @Valid Coordinator coordinator){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(coordinator.getName());
                    recordFound.setCoordination(coordinator.getCoordination());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public List<Coordinator> findCoordinatorByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

}
