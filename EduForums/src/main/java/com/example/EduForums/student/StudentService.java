package com.example.EduForums.student;

//import java.time.LocalDate;
//import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.transaction.annotation.Transactional;



//@Component		// add depencdeny: autowired: impilicit instantiation
@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}



	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	
	public void addNewStudent(Student student) {
//		System.out.println(student);
		
		
		/* BUSINESS LOGIC */
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}
	
	public void deleteStudent(String studentId) {
		//studentRepository.findById(studentId);
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("student with id "+ studentId + " does not exist");
		}
		studentRepository.deleteById(studentId);
	}
	
	
	@Transactional	// we don't have to implement any JPQL query: use setters to automaticatically update the entity in DB
	public void updateStudent(String studentId, String name, String email) {
		
		Student student = studentRepository.findById(studentId)
					.orElseThrow(
								()-> new IllegalStateException("student with id "+ studentId + " doesn't exist")
							);
		
		if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name)) {
			student.setName(name);
		}
		
		if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
				
			}
			student.setEmail(email);
		}
	}
	
}
