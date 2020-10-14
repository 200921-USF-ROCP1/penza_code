package bank.dao.interfaces;

import java.util.List;
import bank.models.Account;
import bank.models.User;

public interface AccountDAO {

		public Account create(Account a) throws Exception;
		
		public Account get(int id) throws Exception;
		
		public void update(Account a) throws Exception;
		
		public void delete(Account a) throws Exception;
		
		public List<Account> getAll(User u) throws Exception;
}
