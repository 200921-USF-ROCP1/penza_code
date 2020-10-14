package bank.services;

import bank.dao.implementations.UserDAOImpl;
import bank.dao.interfaces.UserDAO;
import bank.models.User;

public class UserService {
	public static User registerUser( User user ) throws Exception {
		try {
			UserDAO udao = new UserDAOImpl();
			user = udao.create( user );
			return user;			
		} catch( Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
	
}