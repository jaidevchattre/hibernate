package com.jd.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jd.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
      
		Session session=factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			System.out.println("Deleting student ex 1");
			Student myStudent=session.get(Student.class, 1); 
			session.delete(myStudent);
            session.getTransaction().commit();
            
            session=factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Deleting student ex 2");
            session.createQuery("Delete from Student where id=5")
            .executeUpdate();
            session.getTransaction().commit();
            
            
			
		}finally {
			factory.close();
		}
		
		
	}

}
