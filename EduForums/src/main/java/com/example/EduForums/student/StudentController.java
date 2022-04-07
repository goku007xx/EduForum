package com.example.EduForums.student;



//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

// api/v1/student/1?name=Gauea,
@Controller
@RequestMapping(path="api/v1/student") // localhost/api/vi/student
public class StudentController {

	// reference
	private final StudentService studentService;
	private final StudentView studentView;
	
	@Autowired	// Implicit instatiation of dependency(that are args)
	public StudentController(StudentService studentService, StudentView studentView) {
//		 this.studentService = new StudentService();
		 	// avoid above instead use dependency injection
		
		this.studentService = studentService;
		this.studentView = studentView;
	}

	
	


	@GetMapping(value = {"", "/index"})
	public String index( Model model) {
		model.addAttribute("students", studentService.getStudents());
		return studentView.index(model);		// View
//		return studentService.getStudents();
	}
	
	
	@PostMapping
	 @ResponseBody
	public String registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
		
		return "added";
	}
	
	/*
	@GetMapping(value = {"/add"})
	public String addStudent(@RequestBody Student student){
			
			// ADD LOGIC For view for FORM , so user can input fields
	}
	*/
	
	@DeleteMapping(path = "{studentId}")
	 @ResponseBody
	public String deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
		
		return "deleted";
	}
	
	
	// update
	@PutMapping(path = "{studentId}")
	 @ResponseBody
	public String updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email
			)
	{
		studentService.updateStudent(studentId, name, email);
		
		return "updated";
	}
}
