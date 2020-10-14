package bank.services;

import bank.dao.implementations.UserDAOImpl;
import bank.dao.interfaces.UserDAO;
import bank.models.Login;
import bank.models.User;

public class LoginService {
	public static User loginUser( Login login ) throws Exception {
		UserDAO udao = new UserDAOImpl();
		User user = null;
		user = udao.get(login.getUsername());
		if( user.getPassword().equals(login.getPassword()) ) {
			return user;
		}
		return null;
	}
	
}
