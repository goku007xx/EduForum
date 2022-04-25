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
	
	private String trn;
	
	private String sem;

	public Teacher(String name, Dept dept, String email,String password,String trn, String sem) {
		super(name, dept, email, password);
		
		this.trn = trn;
		this.sem = sem;
	}

	public Teacher(){
		
	}


	public String getTrn() {
		return trn;
	}

	public void setTrn(String trn) {
		this.trn = trn;
	}

	public String getSem() {
		return sem;
	}

	public void setSem(String sem) {
		this.sem = sem;
	}

	@Override
	public String toString() {
		return "Teacher [Dept=" + dept + ", email=" + email + ", id=" + id + ", name=" + name + "sem=" + sem + ", trn=" + trn + "]" ;
	}

	
}
