package controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.*;
public class Course_Controller {
	

	
	EntityManagerFactory emfactory;
	CriteriaBuilder cb ;
	
	public void add_course(Course c)
	{
		
		emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();
		if(check_existance(c))
		{
			entityManager.persist(c);
    		entityManager.getTransaction().commit();
		}
		else
		{
			System.out.println("Course already Exists ");
		}
			

		entityManager.close();
		emfactory.close();
	}

	
	private boolean check_existance(Course c)
	{
		emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();
		cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Course> cq = cb.createQuery(Course.class);
	    Root<Course> course = cq.from(Course.class);
	        cq.where(course.get(Course_.name).in(c.getName()),course.get(Course_.credits).in(c.getCredits()),course.get(Course_.teacher).in(c.getTeacher()));
	        cq.select(course);
	        List<Course> resultList = entityManager.createQuery(cq).getResultList();
	        if (resultList.isEmpty()) return true;
			else return false;
	}
	
	
	public Course course_search(String name)
	{
		Course c=null;
		emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		CriteriaBuilder cb= entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq= cb.createQuery(Course.class);
		Root<Course> courses= cq.from(Course.class);
		cq.select(courses);
		List<Course> rcourses = entityManager.createQuery(cq).getResultList();
		for(Course cc : rcourses)
		{
			if (cc.getName().equalsIgnoreCase(name))
			{
				c=cc;
				break;
			}
		}
		return c;
	}
	
	
}
