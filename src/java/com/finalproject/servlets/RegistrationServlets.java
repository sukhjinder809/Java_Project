/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.servlets;

import com.finalproject.data.DbMgr;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sukhjinder Kaur
 */

public class RegistrationServlets extends HttpServlet {
         	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlets() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
                 @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
                 @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String contactNumber = request.getParameter("contactNumber");
                String sex = request.getParameter("sex");
                String status = request.getParameter("status");
                String course = request.getParameter("course");
                String address = request.getParameter("address");
		
		
		// create connection with database
		ServletContext ctx = getServletContext();
		DbMgr dbMgr = (DbMgr) ctx.getAttribute("DbMgr");

		Connection con = dbMgr.getConnection();
		String insrtQuery = "INSERT INTO students(stu_id, firstName, lastName, email, password, contactNumber,sex,status,course,address)"
				+ " values (?,?,?,?,?,?,?,?,?,?)";
		boolean success = false;
		try {
			PreparedStatement ps = con.prepareStatement(insrtQuery);
			ps.setString(1, firstName);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
                        ps.setString(4, email);
                        ps.setString(5, password);
                        ps.setString(6, contactNumber);
                        ps.setString(7, sex);
                        ps.setString(8, status);
                        ps.setString(9, course);
                        ps.setString(10, address);
			success = ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
                            Logger.getLogger(RegistrationServlets.class.getName()).log(Level.SEVERE, null, e);
		}

		
		RequestDispatcher rd = ctx.getRequestDispatcher("/Login.html");
		PrintWriter out = response.getWriter();
		out.println("<font color=green> Registration complete! " + "Please login</font>");
		rd.include(request, response);
		

	}

}