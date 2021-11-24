package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		try {
			// create a session
			Session session = factory.getCurrentSession();

			// begin transaction
			session.beginTransaction();

			// get a student object to update
			Student myStudent = session.get(Student.class, 2);

			// update student object- no need of session.save()
			myStudent.setFirstName("Jd");

			// commit the updates
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			//update all emails for students
			session.beginTransaction();
			
			System.out.println("Updating email");
			session.createQuery("Update Student set email='abc@gmail.com'")
			.executeUpdate();
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}
