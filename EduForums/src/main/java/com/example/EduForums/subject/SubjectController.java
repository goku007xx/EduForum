package com.example.EduForums.subject;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import com.example.EduForums.student.Student;
import com.example.EduForums.teacher.Teacher;
import com.example.EduForums.topic.Topic;
import com.example.EduForums.topic.TopicService;
import com.example.EduForums.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//
//import com.example.EduForums.subject.Subject;
import com.example.EduForums.subject.SubjectService;


import jakarta.servlet.http.HttpSession;

@Controller
public class SubjectController {
	
private final SubjectService subjectService;

TopicService topicService;
	
	@Autowired
	public SubjectController(SubjectService subjectService,TopicService topicService) {
	    this.subjectService = subjectService;
		this.topicService = topicService;
	}
	
	
	// @GetMapping("")
	// public String viewSubjects(
	// 	@PathVariable("email") String email,
	// 	Model model)
	// {
	// 	model.addAttribute("students", subjectService.getUserSubjects(email));
	// 	return "subjects_view";		// View
	// }


		
	// @GetMapping("/addSub")
	// public String Subjectform(Model model) {
		
	// 	Student sd = new Student();
	// 	model.addAttribute("item", fd);
	// 	return "new_item";
	// 	model.addAttribute("item", fd);
	// 	return "new_item";
	// }
	
	@GetMapping("subject/addsubject")
	public String getSubjectFromUser(Model model , HttpSession session)
	{
		Teacher tdSession = (Teacher) session.getAttribute("teacher");
		System.out.println("Session obj"+ tdSession);

		// no session
		if(tdSession==null)
		{
			String result = "Ma'am/Sir Login before adding a subject";
			model.addAttribute("check", result);
			return "teacher/teacherLoginForm";
		}


		// model.addAttribute("teacher", tdSession);

		Subject sub = new Subject();
		model.addAttribute("subject", sub);

		return "subject/addSubjectForm";
	}
		

	@PostMapping("subject/addsubject")
	public String addSubject(@ModelAttribute("subject") Subject sub ,Model model, HttpSession session)
	{
		System.out.println("without subjecTeacherField " +sub);	// without subjectTeacher
		// return request.toString();

		Teacher tdSession = (Teacher)session.getAttribute("teacher");
		
		if(tdSession==null)
		{
			String result = "Ma'am/Sir Login before adding a subject";
			model.addAttribute("check", result);
			return "teacher/teacherLoginForm";
		}
		else if(sub==null)
		{
			String result = "No subject req body passed last time";
			model.addAttribute("check", result);
			return "subject/addSubjectForm";
		}
		System.out.println(tdSession.getId());

		sub.setSubjectTeacher(tdSession);
		System.out.println(" added subjecTeacherField  from session attr "+sub);


		subjectService.createSubject(sub);
		model.addAttribute("subject", sub);
		model.addAttribute("teacher", tdSession);
		return "redirect:home";
	}
	

	@GetMapping("subject/home")
	public String showSubject(@ModelAttribute("subject") Subject sub,Model model, HttpSession session)
	{
		// TODO
		// Perform logic to check if session obj is owner or has acess before displaying
		Teacher tdSession = (Teacher) session.getAttribute("teacher");

		Student sdSession = (Student) session.getAttribute("student");

		List<Topic> topics;

		


		if(tdSession.getEmail().equals(sub.getSubjectTeacher().getEmail()) || (sub.getSubjectAccess().contains(sdSession.getEmail())) ){
			 topics = sub.getSubjectTopics();
		}
		else{
			 topics = new ArrayList<Topic>();
		}

		model.addAttribute("topics",topics);

		return "subject/home";
	} 


		/* DONE */
	@PutMapping(path="addAccess/{userType}/{subjectCode}")
	@ResponseBody
	public void addUser(
		@PathVariable("userType") String userType,
		@PathVariable("subjectCode") String subjectCode,
		@RequestParam(required = true) String email
		)
	{
		System.out.println(userType+" "+subjectCode);
		if(userType.equals("Student"))
			subjectService.addStudentAccess(subjectCode, email);
		else if(userType.equals("Teacher"))
			subjectService.addTeacherAccess(subjectCode, email);
		else
		{
			System.out.println("INVALID PATH , please specify user type");
		}
	}


	// @PostMapping(path = "addTopic/{subjectCode}")
	// @ResponseBody
	// public void addTopic(
	// 	@PathVariable("subjectCode") String subjectCode,
	// 	@RequestParam(required = true) String email,
	// 	@RequestBody Topic topic
	// 	)
	// {
	// 	subjectService.addTopic(subjectCode, topicName, );
	// }
	
}
