package main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.*;
import model.*;

public class Main {


	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Main m=new Main();
		EntityManagerFactory emfactory=Persistence.createEntityManagerFactory("learning");
		EntityManager entityManager = emfactory.createEntityManager();
		/*m.new_Teacher();*/
		//Course c=m.new_course(entityManager);
	/*	m.new_student();*/
		
		Course c=entityManager.find(Course.class, 2);
		
		Student s=entityManager.find(Student.class,2);
		Student_Controller sc=new Student_Controller();
	///	sc.take_course(s, c);
		///sc.drop_course(s, c);
			///sc.see_credits(s);
		Teacher_Controller tc= new Teacher_Controller();
		//tc.modify_course(2,c);
		///tc.delete_course();
	///tc.course_details(c);
	//x	tc.give_grade(c, s,7);
	///	sc.check_grades(s);
	///	sc.gaind_credits(s);
		
		Course_Controller cc =new Course_Controller();
	//	cc.course_search("edid");
	//	sc.list_available_courses(s);
		
		//System.out.println(sc.login("@bLa", "Boola"));
		
	}



		public void  new_Teacher() {
			Teacher_Controller tc=new Teacher_Controller();
			Teacher t= new Teacher ("tama","bjg","@hoa","tutor");
			tc.add_teacher(t);		
	}
		
		public Course new_course(EntityManager entityManager)
		{

			Teacher t = entityManager.find(Teacher.class, 1);
			Calendar calendar = new GregorianCalendar(2013,0,31);
			Course c=new Course ("edited",5,calendar,t);
			Course_Controller cc=new Course_Controller();
			cc.add_course(c);
			return c;
		}
		
		public void new_student()
		{
			 Student_Controller sc=new Student_Controller();
			 Student s=new Student("nnn","bo","@blakdf");
			 sc.add_student(s);
		}
		

}
