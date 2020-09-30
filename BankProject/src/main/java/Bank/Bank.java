package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Bank {

	public static void main(String[] args) {
		
		// Should probably send the database connection info via the command line,
		// but for now, hardcode it
		String dbConnection = "jdbc:postgresql://lallah.db.elephantsql.com/vicjtlut";
		String dbUser = "vicjtlut";
		String dbPass = "tfxTrmdc2d8ng2vgnJeVpi5dvgDNVMKt";
		
		Connection c = null;
		Statement stmt = null;
		try {
			 Class.forName("org.postgresql.Driver");
			 c = DriverManager.getConnection( dbConnection, dbUser, dbPass );
			 System.out.println("Opened database successfully");
			 
			User u = new User( "cpenza" );
			u.getDBInfo( c );
			 
			 /*
	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );
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
			 */
			 
			 c.close();
			 
		} catch ( Exception e ) {
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			 System.exit(0);
		}

	}

}
