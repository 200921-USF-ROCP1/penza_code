package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserLogin;

public class ServletTest extends HttpServlet {
	
	/**
		Use the given <form> and write a Servlet that would accept a POST request from said form
		You will have to handle all of the reading of the data
		Store the form data in a User object in a Session
		Finally, write a doGet() method for your Servlet that would print all of the User object’s values if one has been created, or “No form has been submitted!” otherwise

	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		User formUser = new User();
		
		formUser.setUsername(request.getParameter("username"));
		formUser.setPassword(request.getParameter("password"));
		formUser.setFavoriteFood(request.getParameter("food"));
		formUser.setLanguagesSpoken( request.getParameterValues("language") );
		
		HttpSession session = request.getSession();
		session.setAttribute("myUser", formUser );
		
		try {
			PrintWriter pw = response.getWriter();
			pw.println( "New user " + formUser.getUsername() + " submitted");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response.setStatus(200);
		response.setContentType("text/html; charset=UTF-8");

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		User u = new User();
		HttpSession session = request.getSession();
		u = (User)session.getAttribute("myUser");
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			PrintWriter pw = response.getWriter();
			if( u == null ) {
				pw.println( "No form has been submitted!");
				response.setStatus(409);
			}
			else {
				pw.println("username: " + u.getUsername() );
				pw.println("password: " + u.getPassword() );
				pw.println("food: " + u.getFavoriteFood() );
				pw.println("language(s): ");
				String[] languages = u.getLanguagesSpoken();
				if( languages.length == 0 ) {
					pw.println("none");
				}
				else {
					for( int i=0; i<languages.length; i++ ) {
						pw.println(languages[i] + "<br>");
					}
				}
				response.setStatus(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}
}
