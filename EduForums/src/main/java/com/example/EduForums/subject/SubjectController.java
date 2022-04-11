package com.example.EduForums.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//import com.example.EduForums.subject.Subject;
//import com.example.EduForums.subject.SubjectService;

@RestController
@RequestMapping(path="") // localhost/api/vi/student
public class SubjectController {
	
private final SubjectService subjectService;
	
	@Autowired
	public SubjectController(SubjectService subjectService) {
	    this.subjectService = subjectService;
	}
	
	
	@GetMapping("/subject")
	public List<Subject> fetchAllSubjects()
	{
		return subjectService.getAllSubjects();
	}
	
}
