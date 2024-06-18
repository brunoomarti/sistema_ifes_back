package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.sistemaifes.sistemaifes.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    UserDetails findByLogin(String login);
    boolean existsByNameIgnoreCase(String name);
}
