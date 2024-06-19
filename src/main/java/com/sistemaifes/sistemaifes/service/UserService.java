package com.sistemaifes.sistemaifes.service;

import java.util.List;

import com.sistemaifes.sistemaifes.controller.AuthenticationController;
import com.sistemaifes.sistemaifes.exception.InvalidLengthException;
import com.sistemaifes.sistemaifes.exception.ItemAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
 
import com.sistemaifes.sistemaifes.dto.request.UserRequestDTO; 
import com.sistemaifes.sistemaifes.dto.response.UserResponseDTO;
import com.sistemaifes.sistemaifes.exception.RecordNotFoundException; 
import com.sistemaifes.sistemaifes.model.User; 
import com.sistemaifes.sistemaifes.repository.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    private AuthenticationController authenticationController;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<UserResponseDTO> getAll() {
        return repository.findAll().stream().map((UserResponseDTO::new)).toList();
    }

    public User findById(@PathVariable @NotNull @Positive Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public User saveUser(UserRequestDTO data){
        User dataUser = new User(data);
        dataUser.setEstahAtivo(true);

        if (dataUser.getName().length() > 100 || dataUser.getName().length() < 3) {
            throw new InvalidLengthException("O nome do usuário deve ter no máximo 100 caracteres e no minimo 3 caracteres");
        }

        if (verifyIUserExist(data.name())){
            throw new ItemAlreadyRegisteredException(data.name());
        }

        return repository.save(dataUser);
    }

    public boolean verifyIUserExist(String equipmentName) {
        return repository.existsByNameIgnoreCase(equipmentName);
    }

    public User update(@NotNull @Positive Long id, @Valid User user){
        return repository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(user.getName());
                    recordFound.setEstahAtivo(user.isEstahAtivo());
                    return repository.save(recordFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull Long id){
        repository.delete(repository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
