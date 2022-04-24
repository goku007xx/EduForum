package com.example.EduForums.user;

import java.lang.ProcessBuilder.Redirect;
import java.util.Optional;

import com.example.EduForums.student.Student;
import com.example.EduForums.teacher.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    // reference
	private final UserService userService;
	
	// private final userView userView;

    @Autowired	// Implicit instatiation of dependency(that are args)
	public UserController(UserService userService) {
//		 this.studentService = new StudentService();
		 	// avoid above instead use dependency injection
		
		this.userService = userService;
		// this.studentView = studentView;
	}

	@GetMapping("")
	public String roleSelect()
	{
		return "user/default";
	}

	// @GetMapping("")
	// public String loginUser(
	// 	@PathVariable("userType") String userType,
	// 	Model model)
	// {
		
	// 	User us = new User();
	// 	model.addAttribute("user", us);
	// 	String userType = "student";
	// 	if(userType == "student"){
	// 		// User us = new User();
	// 		// model.addAttribute("user", us);
	// 		Student sd = new Student();
	// 		model.addAttribute("student", sd);
	// 		return "redirect:student/login";		
	// 	}
	// 	else if(userType=="teacher")
	// 	{
	// 		Teacher td = new Teacher();
	// 		model.addAttribute("teacher", td);
	// 		return "redirect:teacher/login";		
	// 	}
	// 	else{
	// 		// TODO: Admin login logic
	// 		;
	// 	}
	// 	return "user/loginForm";


	// }


	// @PostMapping("login")
	// public void loginApi(@ModelAttribute("user") User us)
	// {
	// 	Boolean isAllOk = userService.authenticateUser(us);

	// 	if(isAllOk){
	// 		// SUCESSFUL LOGIN
	// 		System.out.print("YEAHH");
	// 	}
	// 	else{
	// 		// redirect to relogin
	// 		System.out.println("STUPID LOGIC");
	// 	}

	// }
}
