package bank.dao.interfaces;

import java.util.List;

import bank.models.AccountType;

public interface AccountTypeDAO {
	
	public AccountType get(int id) throws Exception;
	
	public List<AccountType> getAll() throws Exception;

}