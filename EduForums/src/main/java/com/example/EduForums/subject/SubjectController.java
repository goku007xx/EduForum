package com.example.EduForums.subject;

import java.util.List;

import com.example.EduForums.topic.Topic;
import com.example.EduForums.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@RequestMapping(path="/subject") // localhost/api/vi/student
public class SubjectController {
	
private final SubjectService subjectService;
	
	@Autowired
	public SubjectController(SubjectService subjectService) {
	    this.subjectService = subjectService;
	}
	
	
	@GetMapping("")
	public List<Subject> fetchAllSubjects()
	{
		return subjectService.getAllSubjects();
	}


		
	@PostMapping("addSub")
	@ResponseBody
	public void registerNewSubject(@RequestBody Subject subject){
		subjectService.createSubject(subject);
		System.out.println("Subject added");
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
