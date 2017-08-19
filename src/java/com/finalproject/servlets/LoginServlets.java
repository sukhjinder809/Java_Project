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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sukhjinder Kaur
 */
public class LoginServlets extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	out.print("get");
        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd=null;	
        PrintWriter out= response.getWriter();
       out.print("Hello");
         
        String firstName = request.getParameter("stu_id");
	String password = request.getParameter("password");
	String selectQuery = "select stu_id from students where firstName=? and password=?";
		
        ServletContext ctx = getServletContext();
	DbMgr dbMgr = (DbMgr) ctx.getAttribute("DbMgr");

        HttpSession session=request.getSession();
         session.setAttribute("stu_id",firstName);
	Connection con = dbMgr.getConnection();
        out.print("Helloo");
        
	try {
            PreparedStatement ps = con.prepareStatement(selectQuery);
	    ps.setString(1, firstName);
            ps.setString(2, password);
            ResultSet results = ps.executeQuery();
            String stu_id="";
             out.print("Hellooo");
       
            while(results!=null && results.next()){
                stu_id = results.getString("stu_id");
                                }
                  response.setContentType("text/html;charset=UTF-8");
                     
                        
        if(stu_id == null || stu_id==""){
                 rd=ctx.getRequestDispatcher("/Login.html");
                 out.println("<font color=red> User not found</font>");
                 rd.include(request, response);
            }else{
                rd=ctx.getRequestDispatcher("/Update.html");
                out.println("<font color=green> User  found</font>");
                rd.include(request, response);
            }
            
          
        
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlets.class.getName()).log(Level.SEVERE, null, ex);
        }
        }}