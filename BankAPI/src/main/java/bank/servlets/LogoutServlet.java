package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if( username == null ) {
			// No user was logged in
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    response.getWriter().append( "{\r\n"
		    		+ "  \"message\": \"There was no user logged into the session\"\r\n"
		    		+ "}");
		}
		else {
			// Log the user out
			session.invalidate();
			    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"You have successfully logged out " + username + "\"\r\n"
			    		+ "}");
		}
	}

}
