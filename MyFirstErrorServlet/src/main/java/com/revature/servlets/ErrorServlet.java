package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorServlet extends HttpServlet {
	
	/*
	
		Configure the web.xml to route all Exceptions and 404s to ErrorServlet
		Write code that prints information about what kind of error the ErrorServlet is handling to the body of the response using the attributes on the previous slide
		For 2, remember that errors do not always have exceptions, but exceptions always have error codes
		Troubleshooting tip: if the routing does not work in STS’s built-in browser, try a real browser like Chrome, Edge, or Firefox
		To get the template, you could download the whole repository as a zip file, unpack it, and find it in the directory; or simply git clone it and do the same

	*/
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
			String status_code;
			String exception_type;
			String exception_thrown;

			if( e != null ) {
				response.getWriter().println("Exception: " + e.getMessage());
				exception_type = request.getAttribute( "javax.servlet.error.exception_type" ).toString();
				response.getWriter().println( "Exception Type: " + exception_type );
				exception_thrown = request.getAttribute( "javax.servlet.error.exception" ).toString();
				response.getWriter().println( "Exception Thrown: " + exception_thrown );
			}
			
			/*
				javax.servlet.error.status_code - The status code of the error
				javax.servlet.error.exception_type - The exception type, if any
				javax.servlet.error.message - The exception message, if any
				javax.servlet.error.request_uri - The URI of the original request
				javax.servlet.error.exception - The exception object thrown, if any
				javax.servlet.error.servlet_name - The name of the servlet that threw the exception (if one)
			 */
			status_code = request.getAttribute( "javax.servlet.error.status_code" ).toString();
	
			response.getWriter().println( "Error: " + status_code );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
