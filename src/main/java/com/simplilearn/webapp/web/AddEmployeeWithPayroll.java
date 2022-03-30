package com.simplilearn.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.simplilearn.webapp.util.HibernateSessionUtil;

@WebServlet("/add-employee-with-payroll")
public class AddEmployeeWithPayroll extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// add nav top nav bar
		request.getRequestDispatcher("index.jsp").include(request, response);
		request.getRequestDispatcher("add-employee-with-payroll.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set content
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.jsp").include(request, response);

		// fetch data from request
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		double salary = Double.parseDouble(request.getParameter("salary"));
		String dept = request.getParameter("dept");

		double grossAmount = Double.parseDouble(request.getParameter("gross"));
		double netAmount = Double.parseDouble(request.getParameter("net"));
		double tax = Double.parseDouble(request.getParameter("tax"));

		try {
			// 1. build hibernate session factory
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();

			// 2. create session object
			Session session = factory.openSession();

			// 3. begin transaction
			Transaction tx = session.beginTransaction();

			// 4. create persistence object for employee and payroll
			Payroll payroll = new Payroll(grossAmount, netAmount, tax);
			Employee employee = new Employee(firstName, lastName, salary, dept, payroll);

			// 5. save product
			session.save(employee);

			// 6. commit transaction
			tx.commit();
			
			out.print("<h3 style='color:green'> Employee is created with payroll successfully !<h3>");

			// 7. close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Create Employee failed ! <h3>" + e);
		}
	}
}
