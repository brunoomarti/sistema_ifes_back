package com.sistemaifes.sistemaifes.repository;

import com.sistemaifes.sistemaifes.model.NextLessonTeacher;
import com.sistemaifes.sistemaifes.model.StudentLessonSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NextTeacherLessonRepository extends JpaRepository<NextLessonTeacher, Long> {

   @Query(value = """
      WITH NextLesson AS (
           SELECT
               ui.name AS teacher_name,
               d.acronym AS discipline_name,
               loc.name AS local_name,
               TO_TIMESTAMP(CAST(CURRENT_DATE AS VARCHAR) || ' ' || s.start_time, 'YYYY-MM-DD HH24:MI') AS start_time_ts,
               0 AS id_teacherlessonschedule
           FROM
               Teacher te
           JOIN
               User_Ifes ui ON te._id = ui._id
           JOIN
               Lesson l ON te._id = l.id_teacher
           JOIN
               Allocation a ON a.id_lesson = l._id
           JOIN
               Allocation_Schedule als ON a._id = als.allocation_id
           JOIN
               Discipline d ON l.id_discipline = d._id
           JOIN
               Location loc ON a.id_location = loc._id
           JOIN
               Schedule s ON als.schedule_id = s._id
           WHERE
               te.teacher_code = :teacherCode
               AND TO_TIMESTAMP(CAST(CURRENT_DATE AS VARCHAR) || ' ' || s.start_time, 'YYYY-MM-DD HH24:MI') > NOW()
               AND CASE
                   WHEN EXTRACT(DOW FROM CURRENT_DATE) = 1 THEN 'Segunda-feira'
                   WHEN EXTRACT(DOW FROM CURRENT_DATE) = 2 THEN 'Terça-feira'
                   WHEN EXTRACT(DOW FROM CURRENT_DATE) = 3 THEN 'Quarta-feira'
                   WHEN EXTRACT(DOW FROM CURRENT_DATE) = 4 THEN 'Quinta-feira'
                   WHEN EXTRACT(DOW FROM CURRENT_DATE) = 5 THEN 'Sexta-feira'
                   WHEN EXTRACT(DOW FROM CURRENT_DATE) = 6 THEN 'Sábado'
                   WHEN EXTRACT(DOW FROM CURRENT_DATE) = 0 THEN 'Domingo'
               END = a.week_day
           ORDER BY
               TO_TIMESTAMP(CAST(CURRENT_DATE AS VARCHAR) || ' ' || s.start_time, 'YYYY-MM-DD HH24:MI') ASC
           LIMIT 1
      )
      SELECT
           nl.teacher_name,
           nl.discipline_name,
           nl.local_name,
           nl.start_time_ts,
           nl.id_teacherlessonschedule
      FROM
           NextLesson nl;
   """, nativeQuery = true)
   NextLessonTeacher findNextLessonByTeacherCode(String teacherCode);
}
