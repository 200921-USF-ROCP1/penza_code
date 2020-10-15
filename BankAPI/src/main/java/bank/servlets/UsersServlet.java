package bank.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.models.User;
import bank.services.AccountService;
import bank.services.UserService;

public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		String role;

		int status;
		
		try {
			HttpSession session = request.getSession();
			role = (String) session.getAttribute("role");

		    response.setStatus(HttpServletResponse.SC_OK);
			ArrayList<User> userList = UserService.listUsers( role );
			String jsonString = mapper.writeValueAsString(userList);
			response.getWriter().append(jsonString);

		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
