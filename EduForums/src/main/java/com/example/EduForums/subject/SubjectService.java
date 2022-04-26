package com.example.EduForums.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.EduForums.student.Student;
import com.example.EduForums.student.StudentRepository;
import com.example.EduForums.teacher.Teacher;
import com.example.EduForums.teacher.TeacherRepository;
import com.example.EduForums.topic.Topic;
import com.example.EduForums.topic.TopicService;
import com.example.EduForums.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
	private final SubjectRepository subjectRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	private final TopicService topicService;
	
	@Autowired
	public SubjectService(SubjectRepository subjectRepository, TopicService topicService, StudentRepository studentRepository, TeacherRepository teacherRepository) {
	    this.subjectRepository = subjectRepository;
		this.topicService = topicService;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}
	
	
	public List<Subject> getAllSubjects() {
		List<Subject> teas = subjectRepository.findAll();
		for (Subject teach : teas)
        {
			System.out.println(teach);
        }
		return subjectRepository.findAll();
	}

	public Subject getSubject(String subjectCode)
	{
		Optional<Subject> subject = subjectRepository.findSubjectBySubjectCode(subjectCode);
		return subject.get();
	}


	//  pass sub obj
	public void createSubject(Subject subject) {
		/* BUSINESS LOGIC */
		Optional<Subject> subjectCode = subjectRepository.findSubjectBySubjectCode(subject.getSubjectCode());

		if(subjectCode.isPresent()) {
			throw new IllegalStateException("subject code aldready taken!");
		}

		subjectRepository.save(subject);
	}

	public void savesubject(Subject subject) {
		subjectRepository.save(subject);
	}

	public List<Subject> getSubjectsByTeacher(Teacher teacher) {
		//subjectRepository.fin
		return subjectRepository.findSubjectBySubjectTeacher(teacher);
	}




// pass subcode, topic obj, user obj
	public void addTopic(String subjectCode, String topicName, Subject belongsToSubject, User owner)
	{
	
		
		// Optional<Subject> subject = subjectRepository.findSubjectBySubjectCode(subjectCode);

		// if(!subject.isPresent()) {
		// 	throw new IllegalStateException("no such subject, with given code!");
		// }

		Subject subject = subjectRepository.findById(subjectCode)
					.orElseThrow(
								()-> new IllegalStateException("subject with subjectCOde "+ subjectCode + " doesn't exist")
							);
							
		boolean flag = subject.getSubjectAccess().contains(owner);

		if(!flag)
			throw new IllegalStateException("No access to subject with subcode:"+subjectCode+" for user:"+owner);
		else
		{
			Topic topic = topicService.createTopic(topicName, belongsToSubject, owner);
			if(topic!=null)
			{
				ArrayList<Topic> tlist = subject.getSubjectTopics();
				tlist.add(topic);

				// FOr DB
				subject.setSubjectTopics(tlist);
			}

			else
			{
				throw new IllegalStateException("No topic object passed");
			}
		}
		
	}


// pass subcode, student email
	public void addStudentAccess(String subjectCode, String email)
	{
		// Optional<Subject> subject = subjectRepository.findSubjectBySubjectCode(subjectCode);

		// if(!subject.isPresent()) {
		// 	throw new IllegalStateException("no such subject, with given code!");
		// }
		
		// System.out.println("AAXAX"+this.isSubAllowUser(email, subjectCode));
		if(this.isSubAllowUser(email, subjectCode))
		{
			System.out.println("User aldreay has acccess");
			return;
		}

		// System.out.println("VAVAV");
		Student student = studentRepository.findStudentByEmail(email)
					.orElseThrow(
								()-> new IllegalStateException("student with email "+ email + " doesn't exist")
							);



		Subject subject = subjectRepository.findSubjectBySubjectCode(subjectCode)
					.orElseThrow(
								()-> new IllegalStateException("subject with subjectCOde "+ subjectCode + " doesn't exist")
							);
		
		
		// System.out.println(subject.getSubjectAccess().contains(student)+" "+subject.getSubjectAccess());
							
		if(! (student==null))
		{
			ArrayList<User> slist = subject.getSubjectAccess();
			slist.add(student);

			
			// FOr DB
			subject.setSubjectAccess(slist);
			subject = subjectRepository.save(subject);
			System.out.println("added access");
		}

		else
		{
			System.out.println("No user added to sub:");
			System.out.println(subject.getSubjectAccess());
			throw new IllegalStateException("No User object passed");
		}
	}

	// Pass subcode and teacher mail
	public void addTeacherAccess(String subjectCode, String email)
	{
		// Optional<Subject> subject = subjectRepository.findSubjectBySubjectCode(subjectCode);

		// if(!subject.isPresent()) {
		// 	throw new IllegalStateException("no such subject, with given code!");
		// }
		
		if(this.isSubAllowUser(email, subjectCode))
		{
			System.out.println("User aldreay has acccess");
			return;
		}
		
		Teacher teacher = teacherRepository.findTeacherByEmail(email)
					.orElseThrow(
								()-> new IllegalStateException("teacher with email "+ email + " doesn't exist")
							);



		Subject subject = subjectRepository.findSubjectBySubjectCode(subjectCode)
					.orElseThrow(
								()-> new IllegalStateException("subject with subjectCOde "+ subjectCode + " doesn't exist")
							);
			
		if(teacher!=null )
		{
			ArrayList<User> slist = subject.getSubjectAccess();
			slist.add(teacher);

			// FOr DB
			subject.setSubjectAccess(slist);
			subject = subjectRepository.save(subject);
			System.out.println("added access");
			// System.out.println(subject);
		}

		else
		{
			throw new IllegalStateException("No User object passed");
		}
	}

	
	// Display topic and provide option to close for owner: using session
	public List<Topic> getTopicsForSubject(String subjectCode) {
		Subject subject = subjectRepository.findById(subjectCode)
					.orElseThrow(
								()-> new IllegalStateException("subject with subjectCOde "+ subjectCode + " doesn't exist")
							);

		return subject.getSubjectTopics();
    }
	



	public Boolean isSubAllowUser(String email, String subjectCode) {


		Subject subject = subjectRepository.findSubjectBySubjectCode(subjectCode)
					.orElseThrow(
								()-> new IllegalStateException("subject with subjectCOde "+ subjectCode + " doesn't exist")
							);
		
		List<User> list = subject.getSubjectAccess();

		for(User user: list){
			// System.out.println(user.getEmail());
			if(user.getEmail().equals(email))
				return true;
		}

		return false;
	}
}