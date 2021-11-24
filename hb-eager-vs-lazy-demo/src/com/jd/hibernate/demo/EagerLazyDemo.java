package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Course;
import com.jd.hibernate.demo.entity.Instructor;
import com.jd.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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
			System.out.println(tempInstructor1);

			System.out.println(tempInstructor1.getCourses());
			
			session.getTransaction().commit();
			System.out.println("Done!");
			session.close();
			System.out.println("After session close");
			System.out.println(tempInstructor1.getCourses());

			
		}finally {
//			session.close();
			factory.close();
		}
		
		
	}

}
