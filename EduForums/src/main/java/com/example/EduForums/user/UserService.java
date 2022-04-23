package com.example.EduForums.user;

import java.util.Optional;

import com.example.EduForums.student.Student;
import com.example.EduForums.student.StudentRepository;
import com.example.EduForums.student.StudentService;
import com.example.EduForums.teacher.Teacher;
import com.example.EduForums.teacher.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    // private final StudentRepository studentRepository;
    // private final TeacherRepository teacherRepository;
    @Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
        // this.userRepository = userRepository
        // this.studentRepository = studentRepository;
        // this.teacherRepository = teacherRepository;
	}


    // public Boolean authenticateUser(User us){
        
    //     System.out.println(us);
    //     System.out.println(us instanceof Student);
    //     System.out.println(us instanceof Teacher); 
    //     if(us instanceof Student)
	// 	{
	// 		System.out.println("Student trying to login");
	// 		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(us.getEmail());
    //         if(!studentByEmail.isPresent())
    //         {
    //             throw new IllegalStateException("email not there only");
    //         }

    //         else if(studentByEmail.get().getPassword()!=us.getPassword())
    //         {
    //             throw new IllegalStateException("wrong password");
    //         }
    //         else{
    //             return true;
    //         }
            
	// 	}
	// 	else if(us instanceof Teacher)
	// 	{
	// 		System.out.println("teacher trying to login");
           
	// 		Optional<Teacher> teacherByEmail = teacherRepository.findTeacherByEmail(us.getEmail());
    //         if(!teacherByEmail.isPresent())
    //         {
    //             throw new IllegalStateException("email not there only");
    //         }

    //         else if(teacherByEmail.get().getPassword()!=us.getPassword())
    //         {
    //             throw new IllegalStateException("wrong password");
    //         }
    //         else{
    //             return true;
    //         }
	// 	}
    //     else{
    //         System.out.println("INVALID INPUT");
    //     }
        
    //     return false;
    // }

    
}
