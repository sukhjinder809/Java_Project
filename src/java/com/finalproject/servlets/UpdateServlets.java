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
import org.jboss.weld.servlet.SessionHolder;

/**
 *
 * @author Sukhjinder Kaur
 */
public class UpdateServlets extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ex1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ex1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out= response.getWriter();
       //out.print("Hellooooooooooooooooooo");
        String firstName = request.getParameter("stu_id");
	String password = request.getParameter("password");
         out.print(firstName);
         RequestDispatcher rd=null;
       
	String selectQuery = "select stu_id from students where firstName=? and password=?";
		
        ServletContext ctx = getServletContext();
	DbMgr dbMgr = (DbMgr) ctx.getAttribute("DbMgr");

        HttpSession session=request.getSession();
         session.setAttribute("stu_id",firstName);
         
	Connection con = dbMgr.getConnection();
        //out.print("Hellooooooooooooooooooo");
        
	try {
            PreparedStatement ps = con.prepareStatement(selectQuery);
	    ps.setString(1, firstName);
            ps.setString(2, password);
            ResultSet results = ps.executeQuery();
            String stu_id="";
             //out.print("Hellooooooooooooooooooo");
       
            while(results!=null && results.next())
                {
                stu_id = results.getString("stu_id");
                response.setContentType("text/html;charset=UTF-8");
                }
                 
                        
            if(stu_id == null || stu_id==""){
                 rd=ctx.getRequestDispatcher("/Login.html");
                 out.println("<font color=red> User not found</font>");
                 rd.include(request, response);
            }else{
                
                rd=ctx.getRequestDispatcher("/Update.html");
                out.println("<font color=green>User  found</font>");
                rd.include(request, response);
            }
            
          
        
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        String st=(String)session.getAttribute("stu_id");
        
        
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
		String executeupdate = "UPDATE students SET stu_id=?,firstname=?,lastname=?,"
                        + "email=?,password=?,contactNumber=?,sex=?,status=?,address=?"
                        + " where stu_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(executeupdate);
			String stu_id = request.getParameter("stu_id");
			ps.setString(1, firstName);
                        ps.setString(2, firstName);
			ps.setString(3, lastName);
                        ps.setString(4, email);
                        ps.setString(5, password);
                        ps.setString(6, contactNumber);
                        ps.setString(7, sex);
                        ps.setString(8, status);
                        ps.setString(9, address);
                        ps.setString(10, st);
                        
			ps.executeUpdate();
                        session.setAttribute("stu_id",firstName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
                            Logger.getLogger(RegistrationServlets.class.getName()).log(Level.SEVERE, null, e);
		}

		
		RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");
		PrintWriter out = response.getWriter();
		out.println("<font color=green>Update complete!</font>");
		rd.include(request, response);
		

	
       // processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
