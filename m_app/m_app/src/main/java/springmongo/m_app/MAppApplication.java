package springmongo.m_app;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class MAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MAppApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(TeacherRepository repository , MongoTemplate mongoTemplate) {
		return args -> {
			
			String email = "anudeep@gmail.com";
			Teacher teacher = new Teacher("Anudeep","anudeep@gmail.com",Gender.FEMALE,BigDecimal.TEN);
			
			repository.findTeacherByEmail(email).ifPresentOrElse
			(s -> {System.out.println(s + "Teacher already exists");}
			, 
			()-> {System.out.println("Inserting teacher now" + teacher);
				  repository.insert(teacher);
				 }
			);
			
			
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
