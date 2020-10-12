package bank.dao.interfaces;

import java.util.List;

import bank.models.AccountStatus;

public interface AccountStatusDAO {
	
	public AccountStatus get(int id) throws Exception;
	
	public List<AccountStatus> getAll() throws Exception;

}