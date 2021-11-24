package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Course;
import com.jd.hibernate.demo.entity.Instructor;
import com.jd.hibernate.demo.entity.InstructorDetail;
import com.jd.hibernate.demo.entity.Review;
import com.jd.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create Session
		Session session = factory.getCurrentSession();
		
		try {
            //start transaction
			session.beginTransaction();
			
			//get a student 
			int id=4;
			Student student=session.get(Student.class, id);
			
			
			System.out.println("Deleting a student"+student);
			//delete a course
			session.delete(student);
			
			//commit transaction
			session.getTransaction().commit();
			

			
		}finally {
			session.close();			
			factory.close();
		}
	}

}
