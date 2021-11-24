package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Course;
import com.jd.hibernate.demo.entity.Instructor;
import com.jd.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create Session
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			//get instructor from database
			
			int id=1;
			Instructor tempInstructor1=session.get(Instructor.class, id);
			
			//create courses
			Course course1=new Course("Spring");
			Course course2=new Course("SpringBoot");
			
			//add courses to instructor
			tempInstructor1.add(course1);
			tempInstructor1.add(course2);
			
			//save courses
			session.save(course1);
			session.save(course2);
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally {
			session.close();			
			factory.close();
		}
	}

}
