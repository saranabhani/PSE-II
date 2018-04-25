package model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="Result")
@IdClass(Resultid.class)
public class Result {
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + grade;
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		Result other = (Result) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (grade != other.grade)
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}


	@Column(name="grade",nullable=false, updatable=false)
	@Max(5)
	@Min(1)
	private int grade;
	
	@Id
	@ManyToOne
	@JoinColumn(name ="student_code",nullable=false)
	private Student student;
	
	@Id
	@ManyToOne
	@JoinColumn(name="course_code", nullable=false)
	private Course course;

	
	/*Constructors*/
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Result(@Max(5) @Min(1) int grade, Student student, Course course) {
		super();
		this.grade = grade;
		this.student = student;
		this.course = course;
	}


	/*Getters*/
	public int getGrade() {
		return grade;
	}


	public Student getStudent() {
		return student;
	}


	public Course getCourse() {
		return course;
	}


	
	/*Setters*/
	public void setGrade(int grade) {
		this.grade = grade;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
	
	


	
}
