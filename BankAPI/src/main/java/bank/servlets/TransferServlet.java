package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.services.AccountService;

public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TransferServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		TransferRequest tr = new TransferRequest();
		int userid;
		Integer sourceAccountId;
		Integer targetAccountId;
		double amount;
		int status;
		
		try {
			HttpSession session = request.getSession();
			userid = (Integer) session.getAttribute("userid");

			// Get the accountid and amount to withdraw from the request body
			tr = mapper.readValue(request.getReader(), TransferRequest.class);
			sourceAccountId = tr.getSourceAccountId();
			targetAccountId = tr.getTargetAccountId();
			amount = tr.getAmount();
			status = AccountService.transfer( userid, sourceAccountId, targetAccountId, amount );

			// Success
			if( status == 0 ) {
			    response.setStatus(HttpServletResponse.SC_OK);
			    response.getWriter().append( "{\r\n"
			    		+ "  \"message\": \"$" + String.format("%.2f", amount) + " has been transferred from Account #" + sourceAccountId.toString()
			    		+ " to Account #"  + targetAccountId.toString() + "\"\r\n"
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

class TransferRequest {
	
	private int sourceAccountId;
	private int targetAccountId;
	private double amount;
	
	public int getSourceAccountId() {
		return sourceAccountId;
	}
	public void setSourceAccountId(int sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}
	public int getTargetAccountId() {
		return targetAccountId;
	}
	public void setTargetAccountId(int targetAccountId) {
		this.targetAccountId = targetAccountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}
