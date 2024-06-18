package com.sistemaifes.sistemaifes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaifes.sistemaifes.dto.request.LessonRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lesson")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @ManyToOne
    @JoinColumn(name = "id_semester")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "id_discipline")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    private Teacher teacher;

    @Column
    @Positive
    private Integer weeklyQuantity;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Allocation> allocations;

    @Column
    private Boolean allocated;
    
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
        name = "lesson_student",
        joinColumns = @JoinColumn(name = "id_lesson"),
        inverseJoinColumns = @JoinColumn(name = "id_student")
    )
    private List<Student> students;

    public Lesson(LessonRequestDTO data){
        this.semester = data.semester();
        this.discipline = data.discipline();
        this.teacher = data.teacher();
        this.weeklyQuantity = data.weeklyQuantity();
        this.students = data.students();
        this.allocated = data.allocated();
    }
}
