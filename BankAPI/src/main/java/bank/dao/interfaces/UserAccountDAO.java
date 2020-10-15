package bank.dao.interfaces;

import java.util.ArrayList;

import bank.models.UserAccount;

public interface UserAccountDAO {
	
	public UserAccount create(UserAccount a) throws Exception;
	
	public ArrayList<Integer> getAccountOwners(int accountid) throws Exception;
	
	public ArrayList<Integer> getAccountsForUser(int userid) throws Exception;
	
	public void delete(UserAccount a) throws Exception;
	
	public ArrayList<UserAccount> getAll() throws Exception;
	
}
