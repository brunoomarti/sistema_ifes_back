package com.sistemaifes.sistemaifes.controller;

import java.util.Collection;
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

import com.sistemaifes.sistemaifes.dto.request.StudentRequestDTO;
import com.sistemaifes.sistemaifes.dto.request.StudentScheduleRequestDTO;
import com.sistemaifes.sistemaifes.dto.response.StudentResponseDTO;
import com.sistemaifes.sistemaifes.model.Equipment;
import com.sistemaifes.sistemaifes.model.Student;
import com.sistemaifes.sistemaifes.model.StudentSchedule;
import com.sistemaifes.sistemaifes.service.StudentService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public @ResponseBody List<StudentResponseDTO> getAll(){
        return studentService.getAll();
    }
    
    @PostMapping("/new")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Student saveStudent(@RequestBody StudentRequestDTO data){
        System.out.println("\n\n\n" + data + "\n\n\n");
        return studentService.saveStudent(data);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable @NotNull @Positive Long id){
        return studentService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Student update(@PathVariable @NotNull @Positive Long id, @RequestBody Student student){
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        studentService.delete(id);
    }

    @GetMapping("/schedule/{studentCode}")
    public List<StudentSchedule> getStudentSchedule(@PathVariable @NotNull String studentCode){
        System.out.println(studentCode);
        return studentService.getStudentSchedule(studentCode);
    }
}
