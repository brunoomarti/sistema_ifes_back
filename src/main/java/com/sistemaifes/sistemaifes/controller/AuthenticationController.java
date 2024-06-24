package com.sistemaifes.sistemaifes.controller;

import com.sistemaifes.sistemaifes.dto.LoginResponseDTO;
import com.sistemaifes.sistemaifes.infra.security.TokenService;
import com.sistemaifes.sistemaifes.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        User userFind = null;

        var studentFind = repository.findByUserStudentCode(data.login());

        var teacherFind = repository.findByUserTeacherCode(data.login());

        if (studentFind == null) {
            userFind = teacherFind;
        } else {
            userFind = studentFind;
        }

        if (userFind == null) {
            return ResponseEntity.ok(new LoginResponseDTO(data.login(), token, UserRole.ADMIN.toString(), data.login()));
        }

        return ResponseEntity.ok(
                new LoginResponseDTO(userFind.getName(), token, userFind.getRole().toString(), userFind.getLogin())
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRequestDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

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

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid AuthenticationDTO data) {
        User user =  this.repository.findByAllUserWithLogin(data.login());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        if (!validate(data.password())) {
            return ResponseEntity.badRequest().body("A senha deve conter pelo menos um caractere especial e seis caracteres alfanuméricos.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        user.setPassword(encryptedPassword);

        this.repository.save(user);

        return ResponseEntity.ok().build();
    }

    public boolean validate(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[\\p{Punct}])(?=.*[\\w\\d]).{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
