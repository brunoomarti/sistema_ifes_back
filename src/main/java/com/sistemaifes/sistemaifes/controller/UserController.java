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
 
import com.sistemaifes.sistemaifes.dto.request.UserRequestDTO; 
import com.sistemaifes.sistemaifes.dto.response.UserResponseDTO; 
import com.sistemaifes.sistemaifes.model.User;
import com.sistemaifes.sistemaifes.service.UserService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody List<UserResponseDTO> getAll(){
        return userService.getAll();
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User saveUser(@RequestBody UserRequestDTO data){ 
        return userService.saveUser(data);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable @NotNull @Positive Long id){
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable @NotNull @Positive Long id, @RequestBody User user){
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        userService.delete(id);
    }
}
