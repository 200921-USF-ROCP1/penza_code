package bank.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bank.dao.interfaces.UserDAO;
import bank.models.Role;
import bank.models.User;
import bank.services.ConnectionService;

public class UserDAOImpl implements UserDAO {

	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public UserDAOImpl() {
		this.connection = ConnectionService.getConnection();
	}
	
	public void create(User u) throws Exception {
		
		try {
			pstmt = connection.prepareStatement("INSERT INTO users( username, password, firstname, lastName, email, roleid ) values"
	        		+ "	( ?, ?, ?, ?, ?, ? );" );
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getFirstName());
			pstmt.setString(4, u.getLastName());
			pstmt.setString(5, u.getEmail());
			pstmt.setInt(6, u.getRole().getRoleId());
	        pstmt.executeUpdate();		

		} catch(Exception e) {
			 throw e;
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				throw e;
			}
		}		
	};
	
	public User get(int id) throws Exception {
		User u = null;
		Role r = null;
		
		try {
			pstmt = connection.prepareStatement("SELECT u.username AS username, u.password AS password, u.firstname AS firstname,"
					+ " u.lastname AS lastname, u.email AS email, r.roleid AS roleid, r.rolename AS rolename"
					+ " FROM users AS u INNER JOIN role AS r ON u.roleid = r.roleid"
					+ " WHERE u.userid = ?;");
			pstmt.setInt(1, id );
	        pstmt.executeQuery();

			if( rs.next() ) {
				r = new Role( rs.getInt("roleid"), rs.getString("rolename") );
				
				u = new User( rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
								rs.getString("lastname"), rs.getString("email") );
				u.setRole(r);
			}
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				throw e;
			}
		}
		
		return u;
	};
	
	public void update(User u) throws Exception {
		Role r = u.getRole();
		
		try {
			pstmt = connection.prepareStatement("UPDATE users"
					+ " SET username=?, password=?, firstname=?, lastName=?, email=?, roleid=?"
					+ " WHERE u.userid = ?;");
			
			pstmt.setString(1,  u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getFirstName());
			pstmt.setString(4,  u.getLastName());
			pstmt.setString(5,  u.getEmail());
			pstmt.setInt(6, r.getRoleId());
			pstmt.setInt(7, u.getUserid());
			pstmt.executeQuery();

		} catch(Exception e) {
			 throw e;
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				throw e;
			}
		}				
	};
	
	public void delete(User u) throws Exception {
		try {
			pstmt = connection.prepareStatement("DELETE FROM users"
					+ " WHERE u.userid = ?;");
			
			pstmt.setInt(1, u.getUserid());
			pstmt.executeQuery();

		} catch(Exception e) {
			 throw e;
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				throw e;
			}
		}						
	};
	
	public List<User> getAll() throws Exception {
		List<User> userList = new ArrayList<User>();
		User u = null;
		Role r = null;
		
		try {
			pstmt = connection.prepareStatement("SELECT u.username AS username, u.password AS password, u.firstname AS firstname,"
					+ " u.lastname AS lastname, u.email AS email, r.roleid AS roleid, r.rolename AS rolename"
					+ " FROM users AS u INNER JOIN role AS r ON u.roleid = r.roleid;");
	        pstmt.executeQuery();
	        
			while( rs.next() ) {
				r = new Role( rs.getInt("roleid"), rs.getString("rolename") );
				
				u = new User( rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
								rs.getString("lastname"), rs.getString("email") );
				u.setRole(r);
				userList.add(u);
			}

		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				throw e;
			}
		}
		
		return userList;		
	};
}
