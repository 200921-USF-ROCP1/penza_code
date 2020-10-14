package bank.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;

public class ConnectionService {
	// There is a limit of 5 connections using DBeaver, so having a static Connection would not scale well.
	// However, it should satisfy the requirements for this project.
	private static Connection connection;
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				FileInputStream fis = new FileInputStream("C:\\Revature\\OJA\\Git\\penza_code\\BankAPI\\connection.properties");
				Properties prop = new Properties();
				prop.load(fis);
				
				connection = DriverManager.getConnection(prop.getProperty("url"), 
						prop.getProperty("username"), prop.getProperty("password"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}