package com.example.EduForums.subject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.EduForums.teacher.Teacher;
import com.example.EduForums.topic.Topic;
import com.example.EduForums.user.User;

@Document(collection = "Subject")
public class Subject {
	@Id
	private String subjectId;
	private String subjectName;
	
	// @Indexed(unique = false)
	private Teacher subjectTeacher; 
	// list of subs for the subject

	private ArrayList<Topic> subjectTopics;

	private ArrayList<User> subjectAccess;
	

	// @Indexed(unique = true)
	private String subjectCode;
	
	
	
	public Subject( String subjectName, Teacher subjectTeacher, String subjectCode) {
		// this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.subjectTeacher = subjectTeacher;
		this.subjectCode = subjectCode;
		this.subjectTopics = new ArrayList<Topic>();
		this.subjectAccess = new ArrayList<User>();
	}




	public Subject() {
		this.subjectTopics = new ArrayList<Topic>();
		this.subjectAccess = new ArrayList<User>();
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













	public String getSubjectCode() {
		return subjectCode;
	}





	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}


	





	// public Subject(String subjectName, Teacher subjectTeacher, String subjectCode) {
	// 	super();
	// 	this.subjectName = subjectName;
	// 	this.subjectTeacher = subjectTeacher;
	// 	this.subjectCode = subjectCode;
	// 	this.subjectTopics = new ArrayList<Topic>();
	// }
	
	public ArrayList<Topic> getSubjectTopics() {
		return subjectTopics;
	}




	public void setSubjectTopics(ArrayList<Topic> subjectTopics) {
		this.subjectTopics = subjectTopics;
	}




	public ArrayList<User> getSubjectAccess() {
		return subjectAccess;
	}




	public void setSubjectAccess(ArrayList<User> subjectAccess) {
		this.subjectAccess = subjectAccess;
	}




	@Override
	public String toString() {
		return "Subject [subjectAccess=" + subjectAccess + ", subjectCode=" + subjectCode + ", subjectId=" + subjectId
				+ ", subjectName=" + subjectName + ", subjectTeacher=" + subjectTeacher + ", subjectTopics="
				+ subjectTopics + "]";
	}


}
