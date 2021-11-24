package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create Session
		Session session = factory.getCurrentSession();
		
		try {
			//use the session object to save the java object
			System.out.println("create 3 Student");
			//create student object 
			Student tempStudent=new Student("Jai","ABC","jaidevchattre@gmail.com");
			Student tempStudent2=new Student("Sham","PQR","pqr@gmail.com");
			Student tempStudent3=new Student("Ram","XYZ","xyz@gmail.com");
			//start transaction
			System.out.println("Saving student into db");
			session.beginTransaction();
			
			//save the student object
			session.save(tempStudent);
			session.save(tempStudent2);
			session.save(tempStudent3);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			
			
		
		}finally {
			factory.close();
		}
	

	}

}
