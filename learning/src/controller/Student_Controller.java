package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.*;

public class Student_Controller {
	
	
	EntityManagerFactory emfactory;
	CriteriaBuilder cb ;
	
	
	
	public boolean student_login(String email,String pass)
	{
		emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		CriteriaBuilder cb= entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> cq= cb.createQuery(Student.class);
		Root<Student> students= cq.from(Student.class);
		cq.select(students);
		List<Student> rstudents = entityManager.createQuery(cq).getResultList();
		for(Student ss : rstudents)
		{
			if (ss.getEmail().equalsIgnoreCase(email)&& ss.getPassword().equals(pass))
			{
				
				return true;
			}
		}
		
		return false;
	}
	
	
	public void add_student(Student s)
	{
		
		emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();
		if(check_existance(s))
		{
			entityManager.persist(s);
    		entityManager.getTransaction().commit();
		}
		else
		{
			System.out.println("Student already Exists ");
		}
			

		entityManager.close();
		emfactory.close();
	}

	
	private boolean check_existance(Student s)
	{
		emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();
		cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Student> cq = cb.createQuery(Student.class);
	    Root<Student> student = cq.from(Student.class);
	    cq.where(student.get(Student_.name).in(s.getName()),student.get(Student_.email).in(s.getEmail()));
	    cq.select(student);
	    List<Student> resultList = entityManager.createQuery(cq).getResultList();
	    if (resultList.isEmpty()) return true;
	    else return false;
	}

	

	
	
	public void take_course(Student s,Course c)
	{
		emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();
		cb = entityManager.getCriteriaBuilder();
		c.getStudent().add(s);
		entityManager.merge(c);
		entityManager.flush();  
		entityManager.getTransaction().commit();
		entityManager.close();
		emfactory.close();		
	}
	
	
	public void drop_course(Student s,Course c)
	{
		emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();
		cb = entityManager.getCriteriaBuilder();
		c.getStudent().remove(s);
		entityManager.merge(c);
		entityManager.flush();  
		entityManager.getTransaction().commit();
		entityManager.close();
		emfactory.close();		
		
	}
	
	
	public void gaind_credits(Student s)
	{
		int total=0;
		
		for (int i=0; i<s.getResults().size();i++)
		{
			if (s.getResults().get(i).getGrade()>1)
			{
				total+=s.getResults().get(i).getCourse().getCredits();
				System.out.println("Course Name: "+s.getResults().get(i).getCourse().getName()+" , Credits: "+s.getResults().get(i).getCourse().getCredits());
			}
		}
		
		System.out.println("Total credits:" + total);
	}
	
	
	
	
	public void list_taken_courses(Student s)
	{
		int total=0;
		for (int i = 0; i < s.getCourses().size(); i++) {
			System.out.println(s.getCourses().get(i).getName() + ": " + s.getCourses().get(i).getCredits()+" credits");	
			total+=s.getCourses().get(i).getCredits();
		}
		System.out.println(total);
	}
	
	public List<Course> list_available_courses(Student s)
	{
		List<Course> union = new ArrayList<Course>(s.getCourses());
		union.addAll(list_all_courses());
		List<Course> intersection = new ArrayList<Course>(s.getCourses());
		intersection.retainAll(list_all_courses());
		union.removeAll(intersection);
		return union;
	}
	
	public void check_grades(Student s)
	{
		for (int i = 0; i < s.getResults().size(); i++) {
			System.out.println("Course Name:" +s.getResults().get(i).getCourse().getName() + " , Grade: " + s.getResults().get(i).getGrade());			
		}
	}

	
	public List<Course> list_all_courses()
	{
		emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		CriteriaBuilder cb= entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq= cb.createQuery(Course.class);
		Root<Course> courses= cq.from(Course.class);
		cq.select(courses);
		List<Course> rcourses=entityManager.createQuery(cq).getResultList();
		if(rcourses.isEmpty() || rcourses==null) {
			return null;
		}
		return rcourses;
	}
	
	
	
}
