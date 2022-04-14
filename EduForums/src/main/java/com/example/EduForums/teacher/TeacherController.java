package com.example.EduForums.teacher;

import java.util.List;

// import com.example.EduForums.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="/teacher") // localhost/api/vi/student
public class TeacherController {

	private final TeacherService teacherService;
	
	@Autowired
	public TeacherController(TeacherService teacherService) {
	    this.teacherService = teacherService;
	}
	
	
	@GetMapping("")
	public List<Teacher> fetchAllTeachers()
	{
		return teacherService.getAllTeachers();
	}

	// @PostMapping
	// @ResponseBody
	// public String createNewSubject(@RequestBody Subject subject) {
	// 	return "TODO";
	// }
}
