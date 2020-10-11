package bank.dao.implementations;

import java.sql.Connection;
import bank.services.ConnectionService;

public interface AccountDAOImpl {

	Connection connection;
	
	public AccountDAOImpl() {
		this.connection = ConnectionService.getConnection();
	}
	
	public void create(Account a) {
		
	};
	
	public Account get(int id) {
		
	};
	
	public void update(Account a) {
		
	};
	
	public void delete(Account a) {
		
	};
	
	public List<Account> getAll(User u) {
		
	};
}
