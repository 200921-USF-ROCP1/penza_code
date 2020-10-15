package bank.services;

import java.util.ArrayList;

import bank.dao.implementations.UserDAOImpl;
import bank.dao.interfaces.UserDAO;
import bank.models.User;

public class UserService {
	public static User registerUser( User user ) {
		try {
			UserDAO udao = new UserDAOImpl();
			user = udao.create( user );
			return user;			
		} catch( Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<User> listUsers( String role ) {
		ArrayList<User> userList = new ArrayList<User>();
		
		if( !role.equals("Admin") && !role.equals("Employee") ) {
			return null;
		}
		try {
			UserDAO udao = new UserDAOImpl();
			userList = udao.getAll();
			return userList;
		} catch( Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
}