package com.example.EduForums.teacher;

import java.util.ArrayList;
import java.util.List;

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
	SubjectService subjectService;
	
	@Autowired
	public TeacherController(TeacherService teacherService,SubjectService subjectService) {
	    this.teacherService = teacherService;
		this.subjectService = subjectService;
	}
	

	
	@GetMapping("admin/teacher")
	public String fetchAllTeachers(Model model)
	{
		List<Teacher> teachers = teacherService.getAllTeachers();
		
		model.addAttribute("teachers", teachers);
		

		return "teacher/home";
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

		List<Subject> subjects = subjectService.getAllSubjects();
		List<Subject> filterdedSubjects=new ArrayList<Subject>();

		for (int i=0;i<subjects.size();i++){
			if(tdSession.getEmail().equals(subjects.get(i).getSubjectTeacher().getEmail())){
				System.out.println("this is true they are same");
				filterdedSubjects.add(subjects.get(i));
				System.out.println("here is the new filter list"+filterdedSubjects);
				
			}

		}
		
		
		
		System.out.println("Session obj "+tdSession);
		System.out.println("subjects are"+filterdedSubjects);

			// direct access without login
		if(tdSession==null)
		{
			Teacher td = new Teacher();
			model.addAttribute("teacher", td);
			return "redirect:login";
		}
		
		model.addAttribute("teacher", tdSession);
		model.addAttribute("subjects", filterdedSubjects);
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
