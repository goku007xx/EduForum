package com.example.EduForums.student;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository	// Type, ID_type
		extends MongoRepository<Student, String>{
	
	
	Optional<Student> findStudentByEmail(String email);
	
	
	Optional<Student> findById(String studentId);

	
	boolean existsById(String studentId);
	
}
