package com.sistemaifes.sistemaifes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemaifes.sistemaifes.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT t FROM Teacher t WHERE t.coordination._id = :coordinationId")
    List<Teacher> findByCoordinationId(@Param("coordinationId") Long coordinationId);

    @Query("SELECT t FROM Teacher t WHERE t.teacherCode = :teacherCode")
    Teacher idByCode(@Param("teacherCode") String teacherCode);

}
