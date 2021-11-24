package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Instructor;
import com.jd.hibernate.demo.entity.InstructorDetail;

public class GetInstructorBiDemo {

	public static void main(String[] args) {

		// create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();


		// create Session
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			//get instructor detail object
			InstructorDetail instructordetail=session.get(InstructorDetail.class, 3);
			System.out.println("Instructor Detail="+instructordetail);
			
			//print the instructor detail object
			System.out.println("Instructor="+instructordetail.getInstructor());
			
			//print the associated instructor object
			
			session.getTransaction().commit();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
