package bank.servlets;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.fasterxml.jackson.databind.ObjectMapper;

public class FrontControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//ObjectMapper objectMapper = new ObjectMapper();
		
		String path = request.getRequestURI();
		String[] parts = path.split("/");
		
		PrintWriter pw = response.getWriter();
		pw.println( "doPost!!!");

		if( parts[2].equals("login") ) {
			
			pw.println( "Call the login service!" );
		}
		
		// Validate login worked here
		// Set the session info using the login service?
		//Login login = objectMapper.readValue(request.getReader(), Login.class);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String path = request.getRequestURI();
		//String[] parts = path.split("/");
		
		PrintWriter pw = response.getWriter();
		pw.println( "doGet!!!");

	}
}

/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI(); // /FrontController/MyServlet/1?search=hello&id=9&age=17
		String[] parts = path.split("/"); // [ "", "FrontController", "MyServlet", "1"]
		
		request.getQueryString(); // search=hello&id=9&age=17
		String[] queryParameters = request.getQueryString().split("&");
		
		// /cars/:id, /users/:id. I want to call CarDAOImpl.get(id) for one, and userDAOImpl.get(id) for the other
		switch (parts[2]) {
		case "car":
			// I could call a CarService here and pass request and response to deal with them there
			// I could also get the data from the request and pass them to my DAO here
			break;
		case "user":
			break;
		}
		
		// To path match /car/1: /car/*. What if I wanted car/1/version/1. Url path? car/1/version/*
	}

	}*/