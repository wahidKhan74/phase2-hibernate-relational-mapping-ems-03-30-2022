package com.simplilearn.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.webapp.entity.Employee;
import com.simplilearn.webapp.entity.Payroll;
import com.simplilearn.webapp.entity.Project;
import com.simplilearn.webapp.util.HibernateSessionUtil;

@WebServlet("/add-project-with-employee")
public class AddProjectWithEmployee extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		request.getRequestDispatcher("index.jsp").include(request, response);
		request.getRequestDispatcher("add-project-with-employee.html").include(request, response);
	}

	// submitted create action
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.jsp").include(request, response);

		// fetch data from request

		// project multiple project details 
		String P1name = request.getParameter("project1-name");
		String P1no = request.getParameter("project1-no");

		String P2name = request.getParameter("project2-name");
		String P2no = request.getParameter("project2-no");

		// employee personal information
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		double salary = Double.parseDouble(request.getParameter("salary"));
		String dept = request.getParameter("dept");

		// build hibernate session
		try {
			// 1. load session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();

			// 2. create a session
			Session session = factory.openSession();

			// 3. begin transaction
			Transaction tx = session.beginTransaction();

			// 4. create persistence object / add product
			// create a employee object
			Employee employee = new Employee(firstName, lastName, salary, dept);
			session.persist(employee);
			
			//4.1  create project object
			Project p1 = new Project(P1no, P1name);
			p1.setEmployee(employee);
			
			Project p2 = new Project(P2no, P2name);
			p2.setEmployee(employee);
			

			// 5. save product
			session.save(p1);
			session.save(p2);

			// 6. commit transaction
			tx.commit();

			out.print("<h3 style='color:green'> Project is created with employee successfully !<h3>");
			// 3. close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Create Project failed ! <h3>" + e);
		}
	}
}
