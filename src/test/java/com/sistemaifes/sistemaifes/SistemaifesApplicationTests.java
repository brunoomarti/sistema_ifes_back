package com.sistemaifes.sistemaifes;

import com.sistemaifes.sistemaifes.dto.response.LessonResponseDTO;
import com.sistemaifes.sistemaifes.model.Lesson;
import com.sistemaifes.sistemaifes.repository.LessonRepository;
import com.sistemaifes.sistemaifes.service.LessonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SistemaifesApplicationTests {


	@Autowired
	private LessonService lessonService;

	@Autowired
	private LessonRepository lessonRepository;

	@Test
	void testFindLessonsByStudentRegistrationAndSemesterId() {
		// Dados de teste
		String registrationNumber = "123";
		Long semesterId = 1L;

		// Chama o serviço diretamente
		// List<LessonResponseDTO> lessons = lessonService.findLessonsByStudentRegistrationAndSemesterId(registrationNumber, semesterId);

		// Imprime os resultados
		// lessons.forEach(System.out::println);
	}

}
