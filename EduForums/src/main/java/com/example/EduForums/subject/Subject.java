package com.example.EduForums.subject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.EduForums.teacher.Teacher;
import com.example.EduForums.topic.Topic;

@Document(collection = "Subject")
public class Subject {
	@Id
	private String subjectId;
	private String subjectName;
	private Teacher subjectTeacher;
	
	// list of subs for the subject
	private ArrayList<Topic> subjectTopics;
	

	@Indexed(unique = true)
	private String subjectCode;
	
	
	
	public Subject() {
	}
	
	



	public String getSubjectId() {
		return subjectId;
	}





	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}





	public String getSubjectName() {
		return subjectName;
	}





	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}





	public Teacher getSubjectTeacher() {
		return subjectTeacher;
	}





	public void setSubjectTeacher(Teacher subjectTeacher) {
		this.subjectTeacher = subjectTeacher;
	}





	public ArrayList<Topic> getSubjectTopics() {
		return subjectTopics;
	}





	public void setSubjectTopics(ArrayList<Topic> subjectTopics) {
		this.subjectTopics = subjectTopics;
	}





	public String getSubjectCode() {
		return subjectCode;
	}





	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}





	public Subject(String subjectName, Teacher subjectTeacher, String subjectCode) {
		super();
		this.subjectName = subjectName;
		this.subjectTeacher = subjectTeacher;
		this.subjectCode = subjectCode;
		this.subjectTopics = new ArrayList<Topic>();
	}
	
	@Override
	public String toString()
	{
		return String.format("Subject[name='%s']", subjectName);
	}



}
