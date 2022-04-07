package com.example.EduForums.student;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository	// Type, ID_type
		extends JpaRepository<Student, Long >{
	
	// JPQL
	@Query("SELECT s FROM Student s Where s.email=?1")
	Optional<Student> findStudentByEmail(String email);
	
	
}
