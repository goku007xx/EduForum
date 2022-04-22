package com.example.EduForums.student;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
// import java.util.Date;

import com.example.EduForums.user.Dept;
import com.example.EduForums.user.User;

//import org.hibernate.annotations.Entity;
//import org.hibernate.annotations.Table;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Student")	// To say that this class's object will be a collection in Mongo
public class Student extends User{
	
	// @Id
	// private String id;
	// private String name;
	
	// @Indexed(unique = true)
	// private String email;
	
	private String srn;
	// private String section;
	private String sem;
	// private LocalDate dob;
	
	// @Transient			// no need input in db: no need to persist, calculated for us
	// private Integer age;
	

	public Student(String name, Dept dept, String email,String password, String srn,String sem) {
		super(name, dept, email, password);
		this.srn = srn;
		// this.section = section;
		this.sem = sem;
		// this.dob = dob;
		// this.age = age;
	}

	public Student()
	{

	}

	public String getId() {
		return this.id;
	}




	public String getSrn() {
		return srn;
	}


	public void setSrn(String srn) {
		this.srn = srn;
	}


	// public String getSection() {
	// 	return section;
	// }


	// public void setSection(String section) {
	// 	this.section = section;
	// }


	public String getSem() {
		return sem;
	}


	public void setSem(String sem) {
		this.sem = sem;
	}


	// public LocalDate getDob() {
	// 	return dob;
	// }


	// public void setDob(LocalDate dob) {
	// 	this.dob = dob;
	// }


	// public Integer getAge() {
	// 	return Period.between(dob,  LocalDate.now()).getYears();
	// }


	// public void setAge(Integer age) {
	// 	this.age = age;
	// }


	@Override
	public String toString() {
		return "Student [Dept=" + dept + ", email=" + email + ", id=" + id + ", name=" + name +  ", sem=" + sem + ", srn=" + srn + "]";
	}


}
