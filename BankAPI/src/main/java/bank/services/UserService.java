package bank.services;

import java.util.ArrayList;

import bank.dao.implementations.UserDAOImpl;
import bank.dao.interfaces.UserDAO;
import bank.models.User;

public class UserService {
	public static UserDAO udao;
	
	public static User registerUser( User user ) {
		try {
			udao = new UserDAOImpl();
			user = udao.create( user );
			return user;			
		} catch( Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<User> listUsers( User user ) {
		ArrayList<User> userList = new ArrayList<User>();
		String role = user.getRole().getRole();
		
		if( !role.equals("Admin") && !role.equals("Employee") ) {
			return null;
		}
		try {
			udao = new UserDAOImpl();
			userList = udao.getAll();
			return userList;
		} catch( Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static User getUser( User user, int userId ) {
		String role = user.getRole().getRole();
		int curUserId = user.getUserid();
		User listUser = null;
		
		if( role.equals("Admin") || role.equals("Employee") || curUserId == userId ) {
			try {
				udao = new UserDAOImpl();
				listUser = udao.get(userId);
				return listUser;
			} catch( Exception e ) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}