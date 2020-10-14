package bank.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.postgresql.util.PSQLException;

import bank.dao.implementations.AccountDAOImpl;
import bank.dao.implementations.AccountStatusDAOImpl;
import bank.dao.implementations.AccountTypeDAOImpl;
import bank.dao.implementations.UserDAOImpl;
import bank.dao.interfaces.AccountDAO;
import bank.dao.interfaces.AccountStatusDAO;
import bank.dao.interfaces.AccountTypeDAO;
import bank.dao.interfaces.UserDAO;
import bank.models.Account;
import bank.models.AccountStatus;
import bank.models.AccountType;
import bank.models.User;

public class Bank {

	public static void main(String[] args) {
		
		// User Tests

		User u = new User ( "cpenza13", "XYZ", "Christine", "Penza", "christinepenza13@revature.com" );
		User u2 = new User( "testuser1", "badPassword", "Bill", "Gates", "bgates@revature.com" );
		UserDAO udao = new UserDAOImpl();
		
		try {
			// Create new user Test
			u = udao.create(u);
			System.out.println("User " + u.getUsername() + " created");
			 
		} catch ( Exception e ) {
			PSQLException pse = (PSQLException)e;
			System.err.println( e.getClass().getName() + ": " + e.getMessage() + "; state=" + pse.getSQLState() );
			//System.exit(0);
		}

		try {
			// Get test user Test
			u2 = udao.get(1);
			System.out.println("User " + u2.getUsername() + " created");
			System.out.println();
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 //System.exit(0);
		}
		
		try {
			// Get cpenza user
			u2 = udao.get("cpenza");
			System.out.println("User " + u2.getUsername() + " found");
			System.out.println();
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 //System.exit(0);
		}
		
		try {
			// Update new user Test
			u2.setEmail("test@revature.com");
			udao.update(u2);
			System.out.println("User " + u2.getUsername() + " updated");
			System.out.println();
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 //System.exit(0);
		}
		
		try {
			// Delete the user
			System.out.println( "Deleting user with id=" + u.getUserid());
			udao.delete(u);
			System.out.println("User deleted");
			System.out.println();
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 //System.exit(0);
		}
		
		// Get all users		
		try {
			// Delete the user
			System.out.println( "Getting all users..." );
			List<User> userList = new ArrayList<User>();
			userList = udao.getAll();
			for( int i=0; i<userList.size(); i++ ) {
				User curUser = userList.get(i);
				curUser.printInfo();
				System.out.println();
			}
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		// Account statuses
		AccountStatusDAO asdao = new AccountStatusDAOImpl();
		try {
			System.out.println( "Getting all account statuses..." );
			List<AccountStatus> statusList = new ArrayList<AccountStatus>();
			statusList = asdao.getAll();
			for( int i=0; i<statusList.size(); i++ ) {
				AccountStatus acctStatus = statusList.get(i);
				System.out.println("Account Status ID: " + acctStatus.getStatusId());
				System.out.println("Account Status: " + acctStatus.getStatus());
				System.out.println();
			}
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		// Account types
		AccountTypeDAO atdao = new AccountTypeDAOImpl();
		try {
			System.out.println( "Getting all account types..." );
			List<AccountType> typeList = new ArrayList<AccountType>();
			typeList = atdao.getAll();
			for( int i=0; i<typeList.size(); i++ ) {
				AccountType accttype = typeList.get(i);
				System.out.println("Account type ID: " + accttype.getTypeId());
				System.out.println("Account type: " + accttype.getType());
				System.out.println();
			}
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		// Account tests
		Account a = new Account();
		AccountStatus astatus = new AccountStatus();
		AccountType atype = new AccountType();
		AccountDAO adao = new AccountDAOImpl();
		
		try {
			astatus = asdao.get(1);
			a.setStatus(astatus);
			atype = atdao.get(1);
			a.setType(atype);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			Random r = new Random();
			int accountNum = r.nextInt(99999999-10000000 + 1);
			a.setAccountId(accountNum);
			a = adao.create(a);
			a.printInfo();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
