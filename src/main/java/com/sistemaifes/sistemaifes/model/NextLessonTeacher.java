package com.sistemaifes.sistemaifes.model;

import com.sistemaifes.sistemaifes.dto.request.NextLessonTeacherRequestDTO;
import com.sistemaifes.sistemaifes.dto.request.StudentLessonScheduleRequestDTO;
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
public class NextLessonTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_teacherlessonschedule;

    private String teacherName;
    private String disciplineName;
    private String localName;
    private ZonedDateTime startTimeTs;

    public NextLessonTeacher(NextLessonTeacherRequestDTO data){
        this.teacherName = data.teacherName();
        this.disciplineName = data.disciplineName();
        this.localName = data.localName();
        this.startTimeTs = data.startTimeTs();
    }
}
