package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Course;
import com.jd.hibernate.demo.entity.Instructor;
import com.jd.hibernate.demo.entity.InstructorDetail;
import com.jd.hibernate.demo.entity.Review;
import com.jd.hibernate.demo.entity.Student;

public class AddCoursesForStudentsDemo {

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
			int id=2;
			Student tempStudent=session.get(Student.class, id);
			
			System.out.println("Loaded Student"+tempStudent);
			System.out.println("Courses "+tempStudent.getCourses());
			
			//create courses
			Course course1=new Course("Spring and hibernate");
			Course course2=new Course("REST");
			
			//add student to courses
			course1.addStudent(tempStudent);
			course2.addStudent(tempStudent);
			
			//save courses
			session.save(course1);
			session.save(course2);
			//commit transaction
			session.getTransaction().commit();
			

			
		}finally {
			session.close();			
			factory.close();
		}
	}

}
