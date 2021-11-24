package com.jd.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//Create a session factory
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			//begin transaction
			session.beginTransaction();
		    
			List<Student> studentList=session.createQuery("from Student").getResultList();
			System.out.println("Select * from student");
			displayStudents(studentList);
			
			studentList=session.createQuery("from Student s where s.lastName='Chattre'").getResultList();
			
			System.out.println("Select * from student where lastName='Chattre'");
			displayStudents(studentList);
			
			studentList=session.createQuery("from Student s where s.lastName='Chattre'"
					+ " OR s.lastName='ABC'").getResultList();
			System.out.println("Select * from student where lastName='Chattre' or lastName='ABC'");
			displayStudents(studentList);
			
			studentList=session.createQuery("from Student s where"
					+ " s.email LIKE '%@gmail.com'").getResultList();
			
			System.out.println("Select * from student where email LIKE '%@gmail.com'");
			displayStudents(studentList);
			
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
		
				

	}

	private static void displayStudents(List<Student> studentList) {
		for(Student s:studentList)
			System.out.println(s);
	}

}
