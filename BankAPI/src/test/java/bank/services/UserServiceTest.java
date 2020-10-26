package bank.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import bank.dao.interfaces.UserDAO;
import bank.models.Role;
import bank.models.User;

class UserServiceTest {

	@Mock
	UserDAO udao;
	
	@InjectMocks
	UserService userService;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetUser() {
		User searchingUser = new User ( "msmith13", "XYZ", "Mary", "Smith", "msmith@test.com" );
		searchingUser.setUserid(1);
		
		Role role = new Role(1, "Admin" );
		searchingUser.setRole(role);

		User userSearched = UserService.getUser(searchingUser, 2);
		assertNotNull(userSearched);
		assertEquals( "Tony", userSearched.getFirstName());
		
		role = new Role( 2, "Employee" );
		searchingUser.setRole(role);
		userSearched = UserService.getUser(searchingUser, 2);
		assertNotNull(userSearched);
		assertEquals( "Tony", userSearched.getFirstName());

		role = new Role( 3, "Standard" );
		searchingUser.setRole(role);
		userSearched = UserService.getUser(searchingUser, 1);
		assertNotNull(userSearched);
		assertEquals( "Christine", userSearched.getFirstName());
		
		userSearched = UserService.getUser(searchingUser, 2);
		assertNull(userSearched);
	}

}
