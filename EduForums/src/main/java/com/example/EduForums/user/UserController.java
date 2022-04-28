package com.example.EduForums.user;

import java.lang.ProcessBuilder.Redirect;
import java.util.Optional;

import com.example.EduForums.student.Student;
import com.example.EduForums.student.StudentRepository;
import com.example.EduForums.student.StudentService;
import com.example.EduForums.teacher.Teacher;
import com.example.EduForums.teacher.TeacherRepository;
import com.example.EduForums.teacher.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    // reference
	private final UserService userService;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	
	// private final userView userView;

    @Autowired	// Implicit instatiation of dependency(that are args)
	public UserController(UserService userService, StudentRepository studentRepository, TeacherRepository teacherRepository) {
//		 this.studentService = new StudentService();
		 	// avoid above instead use dependency injection
		
		this.userService = userService;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		// this.studentView = studentView;
	}

	// @GetMapping("")
	// public String roleSelect()
	// {
	// 	return "user/default";
	// }

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


	// @GetMapping("admin/adduser")
	// public String addUser()
	// {
	// 	// UserFactory uFactory = new UserFactory();

	// 	return "user/addUserForm";
	// }

	// @PostMapping("admin/adduser")
	// public String registerNewStudent(HttpServletRequest request, Model model) {
		
	// 	UserFactory userFactory = new UserFactory();
		
		
	// 	User us = userFactory.getUser(request.getParameter("usertype"),request.getParameter("name"),Dept.valueOf(request.getParameter("dept")), request.getParameter("email"), request.getParameter("password"), request.getParameter("rn"), request.getParameter("sem"));
		

	// 	if(request.getParameter("usertype").equals("student"))
	// 	{
	// 		// Student s = new Student(us.getName(), us.getDept(), us.getEmail(), us.getEmail(), , sem)
	// 		studentRepository.save((Student)us);
	// 		if(us==null)
	// 		{
	// 			model.addAttribute("check", "Email taken");
	// 			return "admin/adduser";
	// 		}
	// 		else
	// 			return "redirect:teacher";
	// 	}
	// 	else if(request.getParameter("usertype").equals("teacher"))
	// 	{
	// 		us = teacherService.addNewTeacher((Teacher)us);
	// 		if(us==null)
	// 		{
	// 			model.addAttribute("check", "Email taken");
	// 			return "admin/adduser";
	// 		}
	// 		else
	// 			return "redirect:student";
	// 	}

	// 	else
	// 	{
	// 		System.out.println("NO user type");
	// 		return "teacher/addTeacherForm";
	// 	}
	// }
}
