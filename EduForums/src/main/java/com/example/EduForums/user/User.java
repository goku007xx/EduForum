package com.example.EduForums.user;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//import lombok.Data;

//@Data
@Document(collection = "Teacher")	// To say that this class's object will be a collection in Mongo
public class User {
	
	@Id
	protected String id;
	protected String name;
	protected Dept dept;

	protected String password;

	@Indexed(unique = true)
	protected String email;

	public User(String name, Dept dept, String email, String password) {
		this.name = name;
		this.dept = dept;
		this.email = email;
		this.password = password;
	}

	

	public User() {
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
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

	
	public void setDept(Dept dept) {
		this.dept = dept;
	}

	
	public Dept getDept() {
		return dept;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [Dept=" + dept + ", email=" + email + ", id=" + id + ", name=" + name + "]";
	}	
	
}

