package com.example.EduForums;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.EduForums.subject.Subject;
import com.example.EduForums.subject.SubjectRepository;
import com.example.EduForums.teacher.Gender;
//import com.example.EduForums.teacher.MongoTemplate;
import com.example.EduForums.teacher.Teacher;
import com.example.EduForums.teacher.TeacherRepository;
import com.example.EduForums.user.Dept;

//import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class EduForumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduForumsApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(TeacherRepository trepository, SubjectRepository srepository , MongoTemplate mongoTemplate) {
		return args -> {
			
			String email = "anudeep@gmail.com";
			Teacher teacher = new Teacher("Anudeep",Dept.CSE,"anudeep@gmail.com",Gender.FEMALE,BigDecimal.TEN);
			
			trepository.findTeacherByEmail(email).ifPresentOrElse
			(s -> {System.out.println(s + "Teacher already exists");}
			, 
			()-> {System.out.println("Inserting teacher now" + teacher);
				  trepository.insert(teacher);
				 }
			);
			
			String subjectCode = "OOADJ57";
			Subject subject = new Subject("OOADJ",teacher,subjectCode);
			
			srepository.findSubjectBySubjectCode(subjectCode).ifPresentOrElse
			(s -> {System.out.println(s + "Subject already exists");}
			, 
			()-> {System.out.println("Inserting subject now" + subject);
				  srepository.insert(subject);
				 }
			);
			
			System.out.println(teacher.hashCode());
			System.out.println(subject.getSubjectTeacher().hashCode());
			
			
			/* Using MongoTemplateandQuery
			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));
			
			List<Teacher> teachers = mongoTemplate.find(query,Teacher.class);
			
			if(teachers.size() > 1) {
				throw new IllegalStateException("Found more than 1 student for email="+email);
			}
			
			if(teachers.isEmpty()) {
				System.out.println("Inserting teacher now");
				repository.insert(teacher);
			}
			else {
				System.out.println("Teacher already exists");
			}
			*/
			
		
		};

	}

}
