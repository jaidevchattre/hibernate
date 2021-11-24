package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Course;
import com.jd.hibernate.demo.entity.Instructor;
import com.jd.hibernate.demo.entity.InstructorDetail;
import com.jd.hibernate.demo.entity.Review;
import com.jd.hibernate.demo.entity.Student;

public class CreateCoursesAndStudentsDemo {

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
			
			//create a course
			Course tempCourse=new Course("JavaScript");

			//save the course... and leverage the cascade all
			session.save(tempCourse);
			System.out.println("Saving the  course"+tempCourse);
			
			
			//create a student
			Student student1=new Student("Ram","Jadhav","ramjadhav@gmail.com");
			Student student2=new Student("Sandeep","Patil","sandeeppatil93@gmail.com");
			
			//add student to course
			tempCourse.addStudent(student1);
			tempCourse.addStudent(student2);
			
			System.out.println("Saving the  student");
			session.save(student1);
			session.save(student2);
			
			//commit transaction
			session.getTransaction().commit();
			

			
		}finally {
			session.close();			
			factory.close();
		}
	}

}
