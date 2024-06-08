package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import com.sistemaifes.sistemaifes.model.Coordinator;
import com.sistemaifes.sistemaifes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemaifes.sistemaifes.model.Student;
 
public interface StudentRepository extends JpaRepository<Student, Long>  {
    boolean existsByNameIgnoreCase(String name);

    Student findByStudentCode(String studentCode);

    @Query(value = "SELECT s FROM Student s WHERE s.course._id = :cursoId")
    List<User> findByCourseId(Long cursoId);

    @Query("SELECT s FROM Student s WHERE s.studentCode = :studentCode")
    Student idByCode(@Param("studentCode") String studentCode);
}