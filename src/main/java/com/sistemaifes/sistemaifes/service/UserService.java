package com.sistemaifes.sistemaifes.service;

import java.util.List;

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
        return repository.save(dataUser);
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
