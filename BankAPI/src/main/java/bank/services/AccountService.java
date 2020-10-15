package bank.services;

import java.util.ArrayList;

import bank.dao.implementations.AccountDAOImpl;
import bank.dao.implementations.UserAccountDAOImpl;
import bank.dao.implementations.UserDAOImpl;
import bank.dao.interfaces.AccountDAO;
import bank.dao.interfaces.UserAccountDAO;
import bank.dao.interfaces.UserDAO;
import bank.models.Account;
import bank.models.User;

public class AccountService {
	
	// Returns: 0 - success
	//			-1 - invalid permissions
	//			-2 - insufficient funds
	public static int withdraw( int userid, int accountid, double amount ) throws Exception {
		int status = -1;
		boolean okToDeposit = false;
		
		try {
			UserDAO udao = new UserDAOImpl();
			User user = udao.get(userid);
			
			// Admin
			if( user.getRole().getRole().equals("Admin")) {
				okToDeposit = true;
			}
			// Non-Admin; user must own the account
			else {
				UserAccountDAO uadao = new UserAccountDAOImpl();
				ArrayList<Integer> userAccounts = uadao.getAccountOwners(accountid);
				if( userAccounts.contains(userid) ) {
					okToDeposit = true;
				}
			}
			
			if( okToDeposit ) {
				AccountDAO adao = new AccountDAOImpl();
				Account account = adao.get( accountid );
				
				// There are not enough funds in the account.  We are not considering overdraft protection here.
				if( account.getBalance() < amount ) {
					status = -2;
				} else {
					adao.withdraw( account, amount );
					status = 0;
				}
			}
			else {
				status = -1;
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	// Returns: 0 - success
	//			-1 - invalid permissions
	public static int deposit( int userid, int accountid, double amount ) throws Exception {
		int status = 0;
		boolean okToDeposit = false;
		
		try {
			UserDAO udao = new UserDAOImpl();
			User user = udao.get(userid);
			
			// Admin
			if( user.getRole().getRole().equals("Admin")) {
				okToDeposit = true;
			}
			// Non-Admin; user must own the account
			else {
				UserAccountDAO uadao = new UserAccountDAOImpl();
				ArrayList<Integer> userAccounts = uadao.getAccountOwners(accountid);
				if( userAccounts.contains(userid) ) {
					okToDeposit = true;
				}
			}
			
			if( okToDeposit ) {
				AccountDAO adao = new AccountDAOImpl();
				Account account = adao.get( accountid );
				
				adao.deposit( account, amount );
			}
			else {
				status = -1;
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	// Returns: 0 - success
	//			-1 - invalid permissions
	//			-2 - insufficient funds
	public static int transfer( int userid, int sourceAccountId, int targetAccountId, double amount ) throws Exception {
		int status = -1;
		boolean okToTransfer = false;
		
		try {
			UserDAO udao = new UserDAOImpl();
			User user = udao.get(userid);
			
			// Admin
			if( user.getRole().getRole().equals("Admin")) {
				okToTransfer = true;
			}
			// Non-Admin; user must own the source account
			else {
				UserAccountDAO uadao = new UserAccountDAOImpl();
				ArrayList<Integer> userAccounts = uadao.getAccountOwners(sourceAccountId);
				if( userAccounts.contains(userid) ) {
					okToTransfer = true;
				}
			}
			
			if( okToTransfer ) {
				AccountDAO adao = new AccountDAOImpl();
				Account sourceAccount = adao.get( sourceAccountId );
				Account targetAccount = adao.get( targetAccountId );
				
				// There are not enough funds in the account.  We are not considering overdraft protection here.
				if( sourceAccount.getBalance() < amount ) {
					status = -2;
				} else {
					adao.transfer( sourceAccount, targetAccount, amount );
					status = 0;
				}
			}
			else {
				status = -1;
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		return status;
	}
}
