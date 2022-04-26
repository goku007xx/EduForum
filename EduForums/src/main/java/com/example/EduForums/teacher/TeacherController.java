package com.example.EduForums.teacher;

import java.util.List;
import java.util.Optional;

import com.example.EduForums.subject.Subject;
import com.example.EduForums.subject.SubjectService;

// import com.example.EduForums.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;

//import lombok.AllArgsConstructor;

@Controller
public class TeacherController {

	private final TeacherService teacherService;
	private final SubjectService subjectService;
	
	@Autowired
	public TeacherController(TeacherService teacherService,SubjectService subjectService) {
	    this.teacherService = teacherService;
		this.subjectService = subjectService;
	}
	

/* ADMIN SERVICES */	
	@GetMapping("admin/teacher")
	public String fetchAllTeachers(Model model)
	{
		List<Teacher> teachers = teacherService.getAllTeachers();
		model.addAttribute("teachers", teachers);


		return "teacher/teacherList";
	}

	@GetMapping("admin/addteacher")
	public String teacherform(Model model) {
		
		Teacher td = new Teacher();
		model.addAttribute("teacher", td);
		return "teacher/addTeacherForm";
	}

	@PostMapping("admin/addteacher")
	public String registerNewStudent(@ModelAttribute("teacher") Teacher td, Model model) {
		
		td = teacherService.addNewTeacher(td);
		if(td==null)
		{
			model.addAttribute("check", "Email taken");
			return "teacher/addTeacherForm";
		}
		return "redirect:teacher";
	}

	// @PostMapping
	// @ResponseBody
	// public String createNewSubject(@RequestBody Subject subject) {
	// 	return "TODO";
	// }

	
/* Teacher services */

	@GetMapping("teacher/login")
	public String login(Model model, HttpSession session)
	{
		Teacher tdSession = (Teacher) session.getAttribute("teacher");
		System.out.println("Session obj"+ tdSession);
		
			// session aldready started
		if(tdSession!=null)
		{
			model.addAttribute("teacher", tdSession);
			return "redirect:home";
		}
		
		Teacher td = new Teacher();
		model.addAttribute("teacher", td);
		return "teacher/teacherLoginForm";
	}


	@PostMapping("teacher/login")
	public String authStudent(@ModelAttribute("teacher") Teacher td, Model model, HttpSession session)
	{
		Boolean isAllOk = teacherService.authTeacher(td);

		if(!isAllOk)
		{
			// redirect to relogin
			System.out.println("username or pswd wrong");
			String result = "username or pswd wrong";
			model.addAttribute("check",result);
			//return "redirect:login";
			return "teacher/teacherLoginForm";
		}

		td = teacherService.getTeacherAfterLogin(td.getEmail());
		session.setAttribute("teacher", td);
		// model.addAttribute("student", sd);
		return "redirect:home";
	}

	@GetMapping("teacher/home")
	public String home(Model model, HttpSession session)
	{
		
		Teacher tdSession = (Teacher) session.getAttribute("teacher");
		
		System.out.println("Session obj "+tdSession);

			// direct access without login
		if(tdSession==null)
		{
			Teacher td = new Teacher();
			model.addAttribute("student", td);
			return "redirect:login";
		}
		
		System.out.println("Came here");
		List<Subject> subs = subjectService.getSubjectsByTeacher(tdSession);
		System.out.println(subs);
		System.out.println("Printed here");

		model.addAttribute("teacher", tdSession);
		model.addAttribute("subs", subs);
		// System.out.println("model obj "+sd);

		//  Perform check if session is set 
			// TODO: pass student obj from session 
		return "teacher/home";
	}

	@GetMapping("teacher/logout")
	public String end( HttpSession session,  SessionStatus status)
	{

		/*Mark the current handler's session processing as complete, allowing for cleanup of 
  session attributes.*/
		status.setComplete();

/* Invalidates this session then unbinds any objects boundto it. */
		session.invalidate();
	
		return "redirect:../";		// back to home
	}


	
}
