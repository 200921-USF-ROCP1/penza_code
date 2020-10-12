package bank.models;

// For now, the password is stored in clear text.  Fix this!
// https://www.2ndquadrant.com/en/blog/password-authentication-methods-in-postgresql/

public class User {

	private int userid; // primary key
	private String username; // not null, unique
	private String password; // not null
	private String firstName; // not null
	private String lastName; // not null
	private String email; // not null
	private Role role;

	// Create the User object
	public User( String username, String password, String firstName, String lastName, String email ) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public void printInfo() {
		System.out.println( "USERID = " + userid );
		System.out.println( "USERNAME = " + username );
		System.out.println( "PASSWORD = " + password );
		System.out.println( "FIRSTNAME = " + firstName );
		System.out.println( "LASTNAME = " + lastName );
		System.out.println( "EMAIL = " + email );
	}
}
