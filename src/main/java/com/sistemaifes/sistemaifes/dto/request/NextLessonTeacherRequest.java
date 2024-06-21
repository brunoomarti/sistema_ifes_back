package com.sistemaifes.sistemaifes.dto.request;

public record NextLessonTeacherRequest(
        String teacher_name,
        String discipline_name,
        String local_name,
        String start_time_ts,
        Integer id_teacherlessonschedule

) {
}
