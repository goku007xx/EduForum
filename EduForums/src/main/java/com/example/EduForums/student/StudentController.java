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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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


/* ADMIN SERVICES */

	@GetMapping("admin/student")
	public String index( Model model) {
		model.addAttribute("students", studentService.getStudents());
		return "student/studentList";		// View
//		return studentService.getStudents();
	}

	@GetMapping("admin/addStudent")
	public String studentform(Model model) {
		
		Student sd = new Student();
		model.addAttribute("student", sd);
		return "student/addStudentForm";
	}

	@PostMapping("admin/addStudent")
	public String registerNewStudent(@ModelAttribute("student") Student sd) {
		studentService.addNewStudent(sd);
		
		return "redirect:/admin/student";
	}

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



/* STUDENT SERVICES */

	
	@GetMapping("student/login")
	public String login(Model model , HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");

		if(email != null){
			System.out.println("Found session with email="+email);
			return "redirect:home";
		}

		Student sd = new Student();
		model.addAttribute("student", sd);
		return "student/studentLoginForm";
	}
	
	@PostMapping("student/login")
	public String authStudent(@ModelAttribute("student") Student sd, Model model,HttpServletRequest request)
	{
		Boolean isAllOk = studentService.authStudent(sd);

		if(!isAllOk)
		{
			// redirect to relogin
			System.out.println("username or pswd wrong");
			String result = "No";
			model.addAttribute("check",result);
			//return "redirect:login";
			return "student/studentLoginForm";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("email", sd.getEmail());
		session.setMaxInactiveInterval(600);
		return "redirect:home";
	}


	@GetMapping("student/home")
	public String home(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		if(email != null){
			System.out.println("Found session with email="+email);
			return "student/home";
		}
		//  Perform check if session is set 
			// TODO: pass student obj from session 
		return "redirect:login";
	}
	
	

	

	
	
	
	
}
