package com.example.EduForums.student;



// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;

// api/v1/student/1?name=Gauea,
@Controller
// @RequestMapping(path="/student") // localhost/api/vi/student
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

	
	


	@GetMapping("student/home")
	public String index( Model model) {
		model.addAttribute("students", studentService.getStudents());
		return "student/home";		// View
//		return studentService.getStudents();
	}
	

	@GetMapping("student/addStudent")
	public String studentform(Model model) {
		
		Student sd = new Student();
		model.addAttribute("student", sd);
		return "studentForm";
	}


	@PostMapping("student/addStudent")
	public String registerNewStudent(@ModelAttribute("student") Student sd) {
		studentService.addNewStudent(sd);
		
		return "redirect:/student/home";
	}
	
	/*
	@GetMapping(value = {"/add"})
	public String addStudent(@RequestBody Student student){
			
			// ADD LOGIC For view for FORM , so user can input fields
	}
	*/
	
	@DeleteMapping(path = "{studentId}")
	 @ResponseBody
	public String deleteStudent(@PathVariable("studentId") String studentId) {
		studentService.deleteStudent(studentId);
		
		return "deleted";
	}
	
	
	// update
	@PutMapping(path = "{studentId}")
	 @ResponseBody
	public String updateStudent(
			@PathVariable("studentId") String studentId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email
			)
	{
		studentService.updateStudent(studentId, name, email);
		
		return "updated";
	}
}
