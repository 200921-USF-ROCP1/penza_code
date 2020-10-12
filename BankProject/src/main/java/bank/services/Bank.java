package bank.services;


import bank.dao.implementations.UserDAOImpl;
import bank.dao.interfaces.UserDAO;
import bank.models.User;

public class Bank {

	public static void main(String[] args) {

		User u = new User ( "cpenza13", "XYZ", "Christine", "Penza", "christinepenza13@revature.com" );
		User u2 = new User( "testuser1", "badPassword", "Bill", "Gates", "bgates@revature.com" );
		UserDAO udao = new UserDAOImpl();
		
		try {
			// Create new user Test
			u = udao.create(u);
			System.out.println("User " + u.getUsername() + " created");
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 //System.exit(0);
		}

		try {
			// Get test user Test

			u2 = udao.get(1);
			System.out.println("User " + u2.getUsername() + " created");
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 //System.exit(0);
		}
		
		try {
			// Update new user Test
			u2.setEmail("test@revature.com");
			udao.update(u2);
			System.out.println("User " + u2.getUsername() + " updated");
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 //System.exit(0);
		}
		
		try {
			// Delete the user
			System.out.println( "Deleting user with id=" + u.getUserid());
			udao.delete(u);
			System.out.println("User deleted");
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 //System.exit(0);
		}

	}

}
