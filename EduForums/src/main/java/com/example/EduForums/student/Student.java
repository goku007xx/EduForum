package com.example.EduForums.student;

import java.time.LocalDate;
import java.time.Period;

//import org.hibernate.annotations.Entity;
//import org.hibernate.annotations.Table;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Student")	// To say that this class's object will be a collection in Mongo
public class Student {
	
	@Id
	private String id;
	private String name;
	
	@Indexed(unique = true)
	private String email;
	
	private LocalDate dob;
	
	@Transient			// no need input in db: no need to persist, calculated for us
	private Integer age;
	
	public Student() {
		
	}

	public Student(String name, String email, LocalDate dob) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
//		this.age = age;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + this.id + ", name=" + this.name + ", email=" + this.email + ", dob=" + this.dob + ", age=" + this.age + "]";
	}
	
}
