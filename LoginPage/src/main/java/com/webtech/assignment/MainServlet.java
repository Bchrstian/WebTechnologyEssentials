package com.webtech.assignment;

import java.io.IOException; 
import java.io.PrintWriter; 

import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

@WebServlet("/MainServlet") 
public class MainServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L; 
		
	public MainServlet() { 
		super(); 
		// TODO Auto-generated constructor stub 
	} 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

		response.setContentType("text/html"); 
		
		PrintWriter out = response.getWriter(); 
		
		out.print("<html><body>"); 
		out.print("<h2>Welcome to World of Web Technology</h2>"); 
		out.print("</body></html>"); 
	
	} 
}
