package model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="Teacher")
public class Teacher {
	

	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int code;
	
	@Column(name="NAME", nullable=false, length=512, updatable=false)
	private String name;
	
	@Column(name="password", nullable=false, length=512, updatable=false)
	private String password; 
	
	@Column(name="email", nullable=false, length=512, updatable=false)
	private String email;
	
	
	@Column(name="role", nullable=false, length=512, updatable=false)
	private String role;
	
	
	@OneToMany(mappedBy="teacher",cascade=CascadeType.ALL)
	/*@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "Teacher"), inverseJoinColumns = @JoinColumn(name = "Course"))*/
	private List<Course> courses;



	
	/*Constructors*/
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Teacher(String name, String password, String email, String role) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	
	/*Getters*/

	public int getCode() {
		return code;
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


	public String getRole() {
		return role;
	}


	/*public List<Course> getCourses() {
		return courses;
	}*/


	/*Setters*/
	public void setCode(int code) {
		this.code = code;
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


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Teacher other = (Teacher) obj;
		if (code != other.code)
			return false;
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
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}





/*	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}*/
	
	
	
}
