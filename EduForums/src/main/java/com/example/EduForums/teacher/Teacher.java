package com.example.EduForums.teacher;

import java.math.BigDecimal;

import com.example.EduForums.user.Dept;
import com.example.EduForums.user.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//import lombok.Data;

//@Data
@Document(collection = "Teacher")	// To say that this class's object will be a collection in Mongo
public class Teacher extends User{
	
	
	private Gender gender;
	
	private BigDecimal salary;

	public Teacher(String name, Dept dept, String email,String password, Gender gender, BigDecimal salary) {
		super(name, dept, email, password);
		this.gender = gender;
		this.salary = salary;
	}

	public Teacher(){
		
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Teacher [Dept=" + dept + ", email=" + email + ", id=" + id + ", name=" + name + "gender=" + gender + ", salary=" + salary + "]";
	}
	
	
	
}
