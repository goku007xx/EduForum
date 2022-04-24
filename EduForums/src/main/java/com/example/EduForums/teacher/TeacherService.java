package com.example.EduForums.teacher;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import lombok.AllArgsConstructor;

@Service
public class TeacherService {
	
	private final TeacherRepository teacherRepository;
	
	
	@Autowired
	public TeacherService(TeacherRepository teacherRepository) {
	    this.teacherRepository = teacherRepository;
	}
	
	
	public List<Teacher> getAllTeachers() {
		List<Teacher> teas = teacherRepository.findAll();
		for (Teacher teach : teas)
        {
			System.out.println(teach);
        }
		return teacherRepository.findAll();
	}


	public Teacher getTeacherAfterLogin(String email) {
		// NOTE EMAIL GUARANTEED TO EXIST
		return teacherRepository.findTeacherByEmail(email).get();
	}

	public Boolean authTeacher(Teacher td)
	{
		System.out.println("Teacher trying to login");
		System.out.println("req body"+ td.getEmail());
		Optional<Teacher> teacherByEmail = teacherRepository.findTeacherByEmail(td.getEmail());
        if(!teacherByEmail.isPresent())
        {
            System.out.println("email wrong: NO such teacher");
			return false;
        }
        else if(!teacherByEmail.get().getPassword().equals(td.getPassword()))
        {
            System.out.println(teacherByEmail.get().getPassword() +" "+ td.getPassword());
			System.out.println("password is worng");
			return false;
        }
        else{
            return true;
        }
	}

}
