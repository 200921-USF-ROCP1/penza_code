package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bank.models.User;
import bank.services.UserService;

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

			// Non-Admins cannot register new users
			if( role == null || !role.equals("Admin") ) {
			    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"Unauthorized\"\r\n"
			    		+ "}");
			} 
			// Admins
			else {
				
				// In case of invalid JSON
				try {
					user = objectMapper.readValue(request.getReader(), User.class);
					user = UserService.registerUser(user);
					
					// User successfully registered
					if( user != null ) {
						response.setStatus(HttpServletResponse.SC_CREATED);
						String jsonString = mapper.writeValueAsString(user);
						response.getWriter().append(jsonString);
						
					// User email or username already exists; or some other error
					} else {
					    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					    response.getWriter().append( "{\r\n"
					    		+ "  \"message\": \"Invalid fields\"\r\n"
					    		+ "}");
					}
				} catch( JsonParseException jpe ){
				    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				    response.getWriter().append( "{\r\n"
				    		+ "  \"message\": \"Invalid request\"\r\n"
				    		+ "}");
				    jpe.printStackTrace();
				} catch( Exception e ){
				    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				    response.getWriter().append( "{\r\n"
				    		+ "  \"message\": \"Invalid request\"\r\n"
				    		+ "}");
				    e.printStackTrace();
				}
			}
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
