package com.example.EduForums.student;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class StudentView {
	
	public String index(Model model) {
		return "index";
	}


	
	/*
	@GetMapping(value = {'/add'})
	public String add(Model model) {
		return "addUser";
		"redirect:index";
	}
	*/
}


