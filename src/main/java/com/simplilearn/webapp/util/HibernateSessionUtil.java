package com.simplilearn.webapp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.simplilearn.webapp.entity.Employee;
import com.simplilearn.webapp.entity.Payroll;
import com.simplilearn.webapp.entity.Project;

public class HibernateSessionUtil {

	private static SessionFactory factory;
	
	public static SessionFactory buildSessionFactory() {		
		// load configuration
		factory = new Configuration().configure("hibernate.cfg.xml")
				//add mapping
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Payroll.class)
				.addAnnotatedClass(Project.class)
				.buildSessionFactory();
		
		return factory;
	}
}
