package bank.dao.interfaces;

import java.util.List;
import bank.models.Account;
import bank.models.User;

public interface AccountDAO {

		public void create(Account a);
		
		public Account get(int id);
		
		public void update(Account a);
		
		public void delete(Account a);
		
		public List<Account> getAll(User u);
}
