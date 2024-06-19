package com.sistemaifes.sistemaifes.controller;

import com.sistemaifes.sistemaifes.dto.LoginResponseDTO;
import com.sistemaifes.sistemaifes.infra.security.TokenService;
import com.sistemaifes.sistemaifes.model.Coordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistemaifes.sistemaifes.model.User;

import com.sistemaifes.sistemaifes.dto.AuthenticationDTO;
import com.sistemaifes.sistemaifes.dto.request.UserRequestDTO;
import com.sistemaifes.sistemaifes.repository.UserRepository;

import jakarta.validation.Valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        var userFind = repository.findByUserCode(data.login());

        if (userFind == null) {
            return ResponseEntity.ok(new LoginResponseDTO(data.login(), token));
        }

        return ResponseEntity.ok(new LoginResponseDTO(userFind.getName(), token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequestDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        if (!validate(data.password())) {
            return ResponseEntity.badRequest().body("A senha deve conter pelo menos um caractere especial e seis caracteres alfanuméricos.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data);

        newUser.setEstahAtivo(true);
        newUser.setPassword(encryptedPassword);

        this.repository.save(newUser);
        
        return ResponseEntity.ok().build();
    }

    public boolean validate(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[\\p{Punct}])(?=.*[\\w\\d]).{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
