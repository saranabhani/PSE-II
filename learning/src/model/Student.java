package model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="Student")
public class Student {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int S_Code;
	
	@Column(name="NAME", nullable=false, length=512, updatable=false)
	private String name;
	
	@Column(name="password", nullable=false, length=512, updatable=false)
	private String password; 
	
	@Column(name="email", nullable=false, length=512, updatable=false)
	private String email;
	
	@ManyToMany(mappedBy = "students")
    private List<Course> courses;
	
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL)
	private List<Result> results;


	
	
	
	/*Constructors*/
	

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.courses=new ArrayList<Course>();
	}

	
	/*Getters*/
	
	public int getCode() {
		return S_Code;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public List<Result> getResults() {
		return results;
	}

	
	/*Setters*/
	
	public void setCode(int code) {
		S_Code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	
}
