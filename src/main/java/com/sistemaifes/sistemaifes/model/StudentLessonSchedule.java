package com.sistemaifes.sistemaifes.model;

import com.sistemaifes.sistemaifes.dto.request.StudentLessonScheduleRequestDTO;
import com.sistemaifes.sistemaifes.dto.request.StudentScheduleRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentLessonSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_studentlessonschedule;

    private String studentName;
    private String disciplineName;
    private String teacherName;
    private String localName;
    private String completeDisciplineName;
    private ZonedDateTime startTimeTs;

    public StudentLessonSchedule(StudentLessonScheduleRequestDTO data){
        this.studentName = data.studentName();
        this.disciplineName = data.disciplineName();
        this.teacherName = data.teacherName();
        this.localName = data.localName();
        this.completeDisciplineName = data.completeDisciplineName();
        this.startTimeTs = data.startTimeTs();
    }
}
