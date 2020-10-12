package bank.models;

//The AccountStatus model is used to track the status of accounts. Status possibilities are Pending, Open, or Closed, or Denied

public class AccountStatus {
	
	private int statusId;
	private String status;
	
	public int getStatusId() {
		return statusId;
	}
	
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}