package bank.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.models.Account;
import bank.models.User;
import bank.services.AccountService;

public class AccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		
		String path = request.getRequestURI();
		String[] pathParts = path.split("/");
		
		try {
			HttpSession session = request.getSession();
			user = (User) session.getAttribute("user");
			
			// Get a specific account
			if( pathParts.length == 4) {
				if( AccountsServlet.isNumeric(pathParts[3])) {
					int accountid = Integer.parseInt(pathParts[3]);
					Account account = AccountService.getAccount( user, accountid );
					String jsonString = mapper.writeValueAsString(account);
					response.getWriter().append(jsonString);
				    response.setStatus(HttpServletResponse.SC_OK);
				}
			}
			// Get all accounts
			else {

				ArrayList<Account> accountList = AccountService.listAccounts( user );
				String jsonString = mapper.writeValueAsString(accountList);
				response.getWriter().append(jsonString);
			    response.setStatus(HttpServletResponse.SC_OK);
			}

		} catch (Exception e) {
		    e.printStackTrace();
		    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
   public static boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
