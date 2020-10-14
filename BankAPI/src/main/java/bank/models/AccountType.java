package bank.models;

// The AccountType model is used to track what kind of account is being created. Type possibilities are Checking or Savings

public class AccountType {
	
	private int typeId; // primary key
	private String type; // not null, unique
	
	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
