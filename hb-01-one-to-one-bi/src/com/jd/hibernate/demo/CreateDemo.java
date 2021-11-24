package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Instructor;
import com.jd.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//create objects
		Instructor instructor=new Instructor("Ram","Chattre","jaidevchattre@gmail.com");
		
		InstructorDetail instructorDetail= new InstructorDetail("https://www.jd.com/youtube2", "Test2");
		
		instructor.setInstructorDetail(instructorDetail);
		
		//create Session
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			
			//save the instructor object
			// this will also save details object because CascadeType.ALL
            session.save(instructor);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			

			
		}finally {
			factory.close();
		}
	}

}
