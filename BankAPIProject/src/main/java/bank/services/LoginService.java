package bank.services;

import bank.models.Login;

public class LoginService {
	public static void loginUser( Login login ) {
		System.out.println( "Logging in user=" + login.getUsername() );
	}
}
