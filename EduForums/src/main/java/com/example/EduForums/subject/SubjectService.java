package com.example.EduForums.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.EduForums.student.Student;
import com.example.EduForums.topic.Topic;
import com.example.EduForums.user.User;

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


	//  pass sub obj
	public void createSubject(Subject subject) {
		/* BUSINESS LOGIC */
		Optional<Subject> subjectCode = subjectRepository.findSubjectBySubjectCode(subject.getSubjectCode());

		if(subjectCode.isPresent()) {
			throw new IllegalStateException("subject code aldready taken!");
		}

		subjectRepository.save(subject);
	}




// pass subcode, topic obj, user obj
	public void addTopic(String subjectCode, String email, Topic topic)
	{
	
		
		// Optional<Subject> subject = subjectRepository.findSubjectBySubjectCode(subjectCode);

		// if(!subject.isPresent()) {
		// 	throw new IllegalStateException("no such subject, with given code!");
		// }

		Subject subject = subjectRepository.findById(subjectCode)
					.orElseThrow(
								()-> new IllegalStateException("subject with subjectCOde "+ subjectCode + " doesn't exist")
							);
							
		boolean flag=false;
		subject.getSubjectAccess().forEach(user->{
			if(user.getEmail()==email)
			{
				flag=true;
				break;
			}
		});

		if(!flag)
			throw new IllegalStateException("No access to subject with subcode:"+subjectCode+" for user:"+user);

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


// pass subcode, user obj
	public void addAccess(String subjectCode, User user)
	{
		// Optional<Subject> subject = subjectRepository.findSubjectBySubjectCode(subjectCode);

		// if(!subject.isPresent()) {
		// 	throw new IllegalStateException("no such subject, with given code!");
		// }

		Subject subject = subjectRepository.findById(subjectCode)
					.orElseThrow(
								()-> new IllegalStateException("subject with subjectCOde "+ subjectCode + " doesn't exist")
							);
			
		if(user!=null)
		{
			ArrayList<User> slist = subject.getSubjectAccess();
			slist.add(user);

			// FOr DB
			subject.setSubjectAccess(slist);
		}

		else
		{
			throw new IllegalStateException("No User object passed");
		}
	}

	
}
