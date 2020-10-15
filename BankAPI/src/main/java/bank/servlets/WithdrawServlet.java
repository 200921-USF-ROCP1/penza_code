package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.services.AccountService;

public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WithdrawServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		WithdrawRequest wr = new WithdrawRequest();
		int userid;
		Integer accountid;
		double amount;
		int status;
		
		try {
			HttpSession session = request.getSession();
			userid = (Integer) session.getAttribute("userid");

			// Get the accountid and amount to withdraw from the request body
			wr = mapper.readValue(request.getReader(), WithdrawRequest.class);
			accountid = wr.getAccountId();
			amount = wr.getAmount();
			status = AccountService.withdraw( userid, accountid, amount );

			// Success
			if( status == 0 ) {
			    response.setStatus(HttpServletResponse.SC_OK);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"$" + String.format("%.2f", amount) + " has been withdrawn from Account #" + accountid.toString() + "\"\r\n"
			    		+ "}");
			}
			// Invalid request
			else if( status == -1 ) {
			    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"Invalid fields\"\r\n"
			    		+ "}");
			}
			// Insufficient Funds
			else if( status == -2 ) {
			    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"Insufficient funds\"\r\n"
			    		+ "}");				
			}

		} catch (Exception e) {
		    e.printStackTrace();
		}

	}

}

class WithdrawRequest {
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
