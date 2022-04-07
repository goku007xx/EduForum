package com.example.EduForums.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* DB LOADER */

@Configuration
public class StudentConfig {
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student maram = new Student(
					"Maram", "maram@gmail.com", LocalDate.of(2000, Month.JANUARY, 5)
			);
			
			Student alex = new Student(
					"Alex", "alex@gmail.com", LocalDate.of(2000, Month.APRIL, 5)
			);
			
			
			// SAVE TO DB
			repository.saveAll(
					List.of(maram,alex)
			);
		};
	}
}
