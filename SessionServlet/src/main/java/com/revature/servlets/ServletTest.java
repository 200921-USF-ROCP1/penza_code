package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.models.UserLogin;

public class ServletTest extends HttpServlet {
	
	/**
	Create a Servlet that accepts a JSON for its doPost(), given below:
	
	{ username: String, password: String }
	
	
	Next, instead of using a DAO, choose a dummy username and password that would be accepted
	If the username and password are accepted, put a User object that contains the username but NOT the password into the Session
	Finally, create a doGet() for the Servlet that returns the String “<username> is logged in!” if the user is logged in, or “No one is logged in!” otherwise.

	 */
	private static final long serialVersionUID = 1L;
	
	private static final String dummyUsername = "cpenza";
	private static final String dummyPassword = "123";
	private UserLogin dummyUser = new UserLogin(dummyUsername, dummyPassword);

	//Create a Servlet that accepts a JSON for its doPost(), given below:
	// { username: String, password: String }
	
	// HttpServletRequest and HttpServletResponse represent
	// the HTTP request and response messages, including
	// all of the data and metadata we talked about
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
	
		ObjectMapper mapper = new ObjectMapper(); // Create the mapper
		User user1 = new User();
		UserLogin ul = new UserLogin();  //Create a test instance to demarshal
		String jsonString = ""; // get value from http request
		
		// request.getReader();
		//PrintWriter pw;
		
		try {
			ul = mapper.readValue(jsonString, UserLogin.class);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			response.getWriter().println(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		//mapper.readValue(jsonString, UserLogin.class);

		
	}
	


		try {
		// 
		response.getWriter().println("TESTING!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
