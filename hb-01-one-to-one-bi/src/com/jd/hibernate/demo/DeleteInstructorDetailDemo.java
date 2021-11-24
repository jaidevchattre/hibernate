package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Instructor;
import com.jd.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
             int id=3;
			//get instructor detail object
			InstructorDetail instructordetail=session.get(InstructorDetail.class, id);
			System.out.println("Instructor Detail="+instructordetail);
			
			//print the instructor detail object
			System.out.println("Instructor="+instructordetail.getInstructor());
			
			//delete instructor detail object which will do cascade delete
			
			//break the bidirectional link if cascade is not ALL
			instructordetail.getInstructor().setInstructorDetail(null);
			session.delete(instructordetail);
			
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
