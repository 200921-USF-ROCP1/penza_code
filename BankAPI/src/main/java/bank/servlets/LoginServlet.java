package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.models.Login;
import bank.models.User;
import bank.services.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Class.forName("bank.models.Login");
			Login login = objectMapper.readValue(request.getReader(), Login.class);
			user = LoginService.loginUser(login);
			if( user != null ) {
				// Login success
				String jsonString = mapper.writeValueAsString(user);
				response.getWriter().append(jsonString);
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getUsername());
				session.setAttribute("role", user.getRole().getRole());
			} else {
				// Login failure
			    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"Invalid Credentials\"\r\n"
			    		+ "}");
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
