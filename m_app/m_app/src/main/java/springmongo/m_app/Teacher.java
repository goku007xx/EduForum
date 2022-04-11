package springmongo.m_app;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

//@Data
@Document(collection = "Teacher")	// To say that this class's object will be a collection in Mongo
public class Teacher {
	
	@Id
	private String id;
	private String name;
	
	@Indexed(unique = true)
	private String email;
	
	private Gender gender;
	
	private BigDecimal salary;
	
	public Teacher(String name, String email, Gender gender, BigDecimal salary) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.salary = salary;
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
	public String toString()
	{
		return String.format("Teacher[name='%s']", name);
	}
}
