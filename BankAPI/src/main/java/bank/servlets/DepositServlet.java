package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.services.AccountService;

public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DepositServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		DepositRequest dr = new DepositRequest();
		int userid;
		Integer accountid;
		double amount;
		int status;
		
		try {
			HttpSession session = request.getSession();
			userid = (Integer) session.getAttribute("userid");
			
			// Get the accountid and amount to deposit from the request body
			dr = mapper.readValue(request.getReader(), DepositRequest.class);
			accountid = dr.getAccountId();
			amount = dr.getAmount();
			status = AccountService.deposit( userid, accountid, amount );

			// Success
			if( status == 0 ) {
			    response.setStatus(HttpServletResponse.SC_OK);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"$" + amount + " has been deposited to Account #" + accountid.toString() + "\"\r\n"
			    		+ "}");
			}
			// Invalid request
			else if( status == -1 ) {
			    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"Invalid fields\"\r\n"
			    		+ "}");
			}

		} catch (Exception e) {
		    e.printStackTrace();
		}

	}
}

class DepositRequest {
	private int accountId;
	private double amount;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
