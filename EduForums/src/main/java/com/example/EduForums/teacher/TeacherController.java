package com.example.EduForums.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import lombok.AllArgsConstructor;

@RestController
public class TeacherController {

	private final TeacherService teacherService;
	
	@Autowired
	public TeacherController(TeacherService teacherService) {
	    this.teacherService = teacherService;
	}
	
	
	@GetMapping("/trial")
	public List<Teacher> fetchAllTeachers()
	{
		return teacherService.getAllTeachers();
	}
}
