package Bank;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

// For now, the password is stored in clear text.  Fix this!
// https://www.2ndquadrant.com/en/blog/password-authentication-methods-in-postgresql/

public class User {
	private int userId; // primary key
	private String username; // not null, unique
	private String password; // not null
	private String firstName; // not null
	private String lastName; // not null
	private String email; // not null
	// private Role role;

	
	public User( String username, String password, String firstName, String lastName, String email ) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
		// Create the User record in the database
		// get the userId returned from the database call
		// Catch any exceptions
		// REMOVE LATER, but hardcode for now
		this.userId = 1;
	}
	
	public User( String username ) {
		this.username = username;
		
		// Create the User record in the database
		// get the userId returned from the database call
		// Catch any exceptions
		// REMOVE LATER, but hardcode for now
		this.userId = 1;
		
		/* Get the rest of this info from the existing record.  If it doesn't exist, throw an exception
		 * 		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		 */
	}
	
	public void getDBInfo( Connection c ) throws Exception {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM users WHERE userid = " + userId + ";" );
	        while ( rs.next() ) {
	           int userid = rs.getInt("userid");
	           String username = rs.getString("username");
	           String password = rs.getString("password");
	           String firstname = rs.getString("firstname");
	           String lastname = rs.getString("lastname");
	           String email = rs.getString("email");
	           
	           System.out.println( "USERID = " + userid );
	           System.out.println( "USERNAME = " + username );
	           System.out.println( "PASSWORD = " + password );
	           System.out.println( "FIRSTNAME = " + firstname );
	           System.out.println( "LASTNAME = " + lastname );
	           System.out.println( "EMAIL = " + email );
	           System.out.println();
	        }
	        
	        rs.close();
			stmt.close();
		} catch ( Exception e ) {
			 throw e;
		}
	}
}
