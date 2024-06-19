package com.sistemaifes.sistemaifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.sistemaifes.sistemaifes.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    UserDetails findByLogin(String login);
    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT u FROM User u LEFT JOIN Student s ON u._id = s._id JOIN Teacher t ON u._id = t._id WHERE s.studentCode = :userCode OR t.teacherCode = :userCode")
    User findByUserCode(@Param("userCode") String userCode);
}
