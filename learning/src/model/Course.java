package model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="Course")
public class Course {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int C_Code;
	
	@Column(name="NAME", nullable=false, length=512)
	private String name;
	
	@Column(name="CREDITS", nullable=false)
	@Max(8)
	@Min(1)
	private int credits;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time", nullable=false)
	private Calendar start_time;
	
	private Teacher teacher;
	
	
	@JoinTable
    (name = "course_student"
        , 
        joinColumns = { 
            @JoinColumn(name = "C_Code", referencedColumnName = "C_Code")
        }, 
        inverseJoinColumns = { 
            @JoinColumn(name = "S_Code", referencedColumnName = "S_Code")
        }
    )
@ManyToMany
private List<Student> students;
	
	
	
	
	@OneToMany(mappedBy="course",cascade=CascadeType.ALL)
	private List<Result> results;

	
	/*Constructor*/
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Course(String name, @Max(8) @Min(1) int credits,Calendar start_time, Teacher teacher) {
		super();
		this.name = name;
		this.credits = credits;
		this.teacher = teacher;
		this.start_time=start_time;
		this.students=new ArrayList<Student>();
	}


	/*Getters*/
	public int getCode() {
		return C_Code;
	}


	public String getName() {
		return name;
	}


	public int getCredits() {
		return credits;
	}


	public Calendar getStart_time() {
		return start_time;
	}


	public Teacher getTeacher() {
		return teacher;
	}


	public List<Student> getStudent() {
		return students;
	}


	public List<Result> getResults() {
		return results;
	}

	/*Setters*/
	public void setCode(int code) {
		this.C_Code = code;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setCredits(int credits) {
		this.credits = credits;
	}


	public void setStart_time(Calendar start_time) {
		this.start_time = start_time;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	public void setStudent(ArrayList<Student> student) {
		this.students = student;
	}


	public void setResults(List<Result> results) {
		this.results = results;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + C_Code;
		result = prime * result + credits;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
		Course other = (Course) obj;
		if (C_Code != other.C_Code)
			return false;
		if (credits != other.credits)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}





	
	
	

	
}
