package com.example.EduForums.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.example.EduForums.user.Dept;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
/* DB LOADER */

@Configuration
public class StudentConfig {
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			String email1 = "maram@gmail.com";
			Student maram = new Student(
					"Maram", Dept.CSE, email1, "cs467", "6"
			);
			

			String email2 = "alex@gmail.com";
			Student alex = new Student(
				"Alex", Dept.ECE, email2, "ec467", "4");
			
			
			repository.findStudentByEmail(email1).ifPresentOrElse
			(s -> {System.out.println(s + "Student already exists");}
			, 
			()-> {System.out.println("Inserting Student now" + maram);
					System.out.println(maram);
					System.out.println("AAAA");
					repository.insert(maram);
				 }
			);

			repository.findStudentByEmail(email2).ifPresentOrElse
			(s -> {System.out.println(s + "Student already exists");}
			, 
			()-> {System.out.println("Inserting Student now" + alex);
			System.out.println(alex);
			System.out.println("AAAA");
					repository.insert(alex);
				}
			);
		};
	}
}
