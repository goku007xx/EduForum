package com.example.EduForums.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
private final SubjectRepository subjectRepository;
	
	
	@Autowired
	public SubjectService(SubjectRepository subjectRepository) {
	    this.subjectRepository = subjectRepository;
	}
	
	
	public List<Subject> getAllSubjects() {
		List<Subject> teas = subjectRepository.findAll();
		for (Subject teach : teas)
        {
			System.out.println(teach);
        }
		return subjectRepository.findAll();
	}
}
