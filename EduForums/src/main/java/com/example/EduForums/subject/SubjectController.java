package com.example.EduForums.subject;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import com.example.EduForums.student.Student;
import com.example.EduForums.teacher.Teacher;
import com.example.EduForums.topic.Topic;
import com.example.EduForums.topic.TopicRepository;
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
//import com.example.EduForums.subject.SubjectService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SubjectController {

	private final SubjectService subjectService;
	private final TopicService topicService;

	@Autowired
	public SubjectController(SubjectService subjectService , TopicService topicService) {
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
		// model.addAttribute("subject", sub);
		model.addAttribute("teacher", tdSession);
		return "redirect:"+sub.getSubjectCode();
	}
	

	@GetMapping("subject/{subjectCode}")
	public String showSubject(	@PathVariable("subjectCode") String subjectCode, Model model, HttpSession session)
	{
		// TODO
		// Perform logic to check if session obj is owner or has acess before displaying
		Subject subject = subjectService.getSubject(subjectCode);
		model.addAttribute("subject", subject);

		List<Topic> topics_list = topicService.getTopicsBySubject(subject);
		model.addAttribute("topics", topics_list);

		return "subject/home";
	} 

		// NOT PROPER
	// 	/* DONE */
	// @PutMapping(path="addAccess/{userType}/{subjectCode}")
	// @ResponseBody
	// public void addUser(
	// 	@PathVariable("userType") String userType,
	// 	@PathVariable("subjectCode") String subjectCode,
	// 	@RequestParam(required = true) String email
	// 	)
	// {
	// 	System.out.println(userType+" "+subjectCode);
	// 	if(userType.equals("Student"))
	// 		subjectService.addStudentAccess(subjectCode, email);
	// 	else if(userType.equals("Teacher"))
	// 		subjectService.addTeacherAccess(subjectCode, email);
	// 	else
	// 	{
	// 		System.out.println("INVALID PATH , please specify user type");
	// 	}
	// }

	// Proper 
	@GetMapping("subject/addtopic")
	public String addtopic(Model model , HttpSession session)
	{

		// no session
		User udSession = (User) session.getAttribute("teacher");
		if(udSession==null)
		{
			udSession = (User) session.getAttribute("student");
			if(udSession==null)
			{
				System.out.println("Need to login");
				return "redirect:../";
			}
		}
		System.out.println("Session obj"+ udSession);



		// model.addAttribute("teacher", tdSession);

		Topic topic = new Topic();
		model.addAttribute("topic", topic);
		return "subject/addTopicForm";
	}

	// Proper Done Byt Gokul
	@PostMapping("subject/addtopic")
	public String addtopic(@ModelAttribute("topic") Topic topic ,Model model, HttpSession session,HttpServletRequest request)
	{
		//System.out.println("without subjecTeacherField " +sub);	// without subjectTeacher
		// return request.toString();

		User udSession = (User) session.getAttribute("teacher");
		if(udSession==null)
		{
			udSession = (User) session.getAttribute("student");
			if(udSession==null)
			{
				System.out.println("Need to login");
				return "redirect:../../";
			}
		}
		System.out.println("Session obj"+ udSession);
		
		if(topic==null)
		{
			String result = "No topic req body passed last time";
			model.addAttribute("check", result);
			return "subject/addTopicForm";
		}
		//System.out.println(tdSession.getId());
		String subjectCode = request.getParameter("subjectCode");
		System.out.println("SubjectCode="+subjectCode);
		//sub.setSubjectTeacher(tdSession);
		Subject sub = subjectService.getSubject(subjectCode);

		topic.setBelongsToSubject(sub);	// MISTAKE TO BE FIXED BY GAURAV/GURUKIRAN

		if(!subjectService.isSubAllowUser(udSession.getEmail(), topic.getBelongsToSubject().getSubjectCode()))
		{
			System.out.println("NO access");
			model.addAttribute("check", "No access");
			return "redirect:../";
		}
		topic.setOwner(udSession);
		topicService.saveTopic(topic);

		ArrayList<Topic> topic_of_sub = sub.getSubjectTopics();
		topic_of_sub.add(topic);
		sub.setSubjectTopics(topic_of_sub);

		subjectService.savesubject(sub);

		
		//topic.getBelongsToSubject().setSubjectTopics(topic_of_sub);
		//subjectService.savesubject(topic.getBelongsToSubject());

		
		//topicService.saveTopic(topic);
		// subjectService.savesubject(sub);
		System.out.println(" added belongstosub Field "+topic);

		//subjectService.createSubject(sub);
		//model.addAttribute("subject", sub);
		//model.addAttribute("teacher", tdSession);
		Subject subject = subjectService.getSubject(subjectCode);
		model.addAttribute("subject", subject);

		List<Topic> topics_list = topicService.getTopicsBySubject(subject);
		model.addAttribute("topics", topics_list);
		return "redirect:"+subjectCode;
	}


		// model.addAttri


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

	
	// Proper 
	@GetMapping("subject/addaccess")
	public String addAccess(Model model , HttpSession session)
	{
		


		// model.addAttribute("teacher", tdSession);

		// model.addAttribute("user", udSession);
		return "subject/addAccessForm";
	}



	// Proper Done Byt Gokul
	@PostMapping("subject/addaccess")
	public String addAccess(Model model, HttpSession session,HttpServletRequest request)
	{
		//System.out.println("without subjecTeacherField " +sub);	// without subjectTeacher
		// return request.toString();

			// CHECKED IN GET ROUTE
		// User tdSession = (User)session.getAttribute("teacher");
		
		// if(tdSession==null)
		// {
		// 	String result = "Ma'am/Sir Login before adding a subject";
		// 	model.addAttribute("check", result);
		// 	return "teacher/teacherLoginForm";
		// }

		User udSession = (User) session.getAttribute("teacher");
		if(udSession==null)
		{
			udSession = (User) session.getAttribute("student");
			if(udSession==null)
			{
				System.out.println("Need to login");
				return "redirect:../../";
			}
		}
		System.out.println("Session obj"+ udSession);
		
		// if(us==null)
		// {
		// 	String result = "Not logged in";
		// 	model.addAttribute("check", result);
		// 	return "subject/addAccessForm";
		// }
		//System.out.println(tdSession.getId());
		String subjectCode = request.getParameter("subjectCode");
		System.out.println("SubjectCode="+subjectCode);
		//sub.setSubjectTeacher(tdSession);
		Subject sub = subjectService.getSubject(subjectCode);

		// topic.setBelongsToSubject(sub);	// MISTAKE TO BE FIXED BY GAURAV/GURUKIRAN
		// topicService.saveTopic(topic);
		
			/* ALDREADY HAS ACCESS */
		System.out.println(subjectService.getSubjectByAccess(udSession).contains(sub));
		if(subjectService.getSubjectByAccess(udSession).contains(sub))
		{
			System.out.println("ALDREADY HAS ACCESS ");
			return "redirect:../";
		}

		ArrayList<User> access_of_sub = sub.getSubjectAccess();
		access_of_sub.add(udSession);
		sub.setSubjectAccess(access_of_sub);

		subjectService.savesubject(sub);

		
		//topic.getBelongsToSubject().setSubjectTopics(topic_of_sub);
		//subjectService.savesubject(topic.getBelongsToSubject());

		
		//topicService.saveTopic(topic);
		// subjectService.savesubject(sub);
		// System.out.println(" added belongstosub Field "+topic);
		System.out.println("Added access for user "+udSession.getEmail());
		//subjectService.createSubject(sub);
		model.addAttribute("subject", sub);
		//model.addAttribute("teacher", tdSession);
		Subject subject = subjectService.getSubject(subjectCode);
		model.addAttribute("subject", subject);

		List<Topic> topics_list = topicService.getTopicsBySubject(subject);
		model.addAttribute("topics", topics_list);
		return "redirect:"+subjectCode;
	}
	
}
