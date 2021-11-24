package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jd.hibernate.demo.entity.Course;
import com.jd.hibernate.demo.entity.Instructor;
import com.jd.hibernate.demo.entity.InstructorDetail;

//use hibernate query .query and not persistent one
public class FetchJoinDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create objects
		Instructor instructor=new Instructor("Ram","Chattre","jaidevchattre@gmail.com");
		
		InstructorDetail instructorDetail= new InstructorDetail("https://www.jd.com/youtube2", "Test2");
		
		instructor.setInstructorDetail(instructorDetail);
		
		//create Session
		Session session = factory.getCurrentSession();
		
		try {

			session.beginTransaction();
			//get instructor from database
			
			int id=1;
//			Instructor tempInstructor1=session.get(Instructor.class, id);
			Query<Instructor> query=session.createQuery("Select i from Instructor i "
                                                  + "JOIN FETCH i.courses "
					                              + "where i.id=:theInstructorId",Instructor.class);
			
			query.setParameter("theInstructorId",id);
			Instructor tempInstructor1=query.getSingleResult();
			System.out.println(tempInstructor1);

			System.out.println(tempInstructor1.getCourses());
			
			session.getTransaction().commit();
			System.out.println("Done!");
			session.close();
			System.out.println("After session close");
			System.out.println(tempInstructor1.getCourses());

			
		}finally {
//			session.close();
			factory.close();
		}
		
		
	}

}
