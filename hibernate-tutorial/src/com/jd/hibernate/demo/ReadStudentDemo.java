package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			System.out.println("create a Student");
			//create student object 
			Student tempStudent=new Student("Tom","Cruise","tom@gmail.com");
			//start transaction
			System.out.println("Saving student into db");
			session.beginTransaction();
			
			//save the student object
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			//get a session and start a transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve the student 
			Student myStudent=session.get(Student.class,tempStudent.getId());
			System.out.println("Get completed: "+myStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		
		}finally {
			factory.close();                 
		}
	}

}
