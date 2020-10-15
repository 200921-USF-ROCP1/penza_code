package bank.dao.interfaces;

import java.util.List;
import bank.models.Account;
import bank.models.User;

public interface AccountDAO {

		public Account create(Account a) throws Exception;
		
		public Account get(int id) throws Exception;
		
		public void update(Account a) throws Exception;
		
		// Adding a separate withdraw method as an update operation.
		// Otherwise, if multiple account owners or Admins were updating at the same time,
		// the correct amount may not actually be withdrawn.
		public void withdraw(Account a, double amount) throws Exception;
		
		// Adding a separate deposit method as an update operation.
		// Otherwise, if multiple account owners or Admins were updating at the same time,
		// the correct amount may not actually be deposited.
		public void deposit(Account a, double amount) throws Exception;
		
		// Adding a separate transfer method as a transaction.

		public void transfer(Account sourceAccount, Account targeAccount, double amount) throws Exception;
		
		public void delete(Account a) throws Exception;
		
		public List<Account> getAll(User u) throws Exception;
}
