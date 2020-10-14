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
import bank.services.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		ObjectMapper mapper = new ObjectMapper();
		String role = null;
		
		try {
			HttpSession session = request.getSession();
			role = (String) session.getAttribute("role");
			if( role == null || !role.equals("Admin") ) {
				// Non-Admins cannot register new users
			    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"Unauthorized\"\r\n"
			    		+ "}");
			} else {
				user = objectMapper.readValue(request.getReader(), User.class);
				user = UserService.registerUser(user);
				if( user != null ) {
					// User successfully created
					response.setStatus(HttpServletResponse.SC_CREATED);
					String jsonString = mapper.writeValueAsString(user);
					response.getWriter().append(jsonString);
				} else {
					// User email or username already exists; or some other error
				    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				    response.getWriter().append( "{\r\n"
				    		+ "  \"message\": \"Invalid fields\"\r\n"
				    		+ "}");
				}
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
