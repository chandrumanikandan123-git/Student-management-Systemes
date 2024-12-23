package com.web.jdbc;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web_student_trackers")
	private DataSource dataSource;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    PrintWriter out = response.getWriter();
		    response.setContentType("text/plain");

		    try (Connection myConn = dataSource.getConnection();
		         Statement myStmt = myConn.createStatement();
		         ResultSet myRs = myStmt.executeQuery("select * from student")) {

		        // Process the result set
		        while (myRs.next()) {
		            String firstName = myRs.getString("first_name");
		            String email = myRs.getString("email");
		
		            out.println(firstName);
		            out.println(email);
		        }
		    } catch (Exception exc) {
		        exc.printStackTrace();
		  
		    }}}
