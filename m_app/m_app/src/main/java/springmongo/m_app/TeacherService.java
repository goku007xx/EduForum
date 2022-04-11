package springmongo.m_app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

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

}
