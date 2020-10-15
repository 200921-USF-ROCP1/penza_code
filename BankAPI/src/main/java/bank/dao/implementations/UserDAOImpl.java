package bank.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	public User create(User u) throws Exception {
		Role r = null;
		String stmt = null;
		
		try {
			r = u.getRole();

			stmt = "INSERT INTO users( username, password, firstname, lastName, email";
			if( r != null ) {
				stmt = stmt + ", roleid";
			}
			stmt = stmt + " ) values"
	        	+ "	( ?, ?, ?, ?, ?";
			if( r != null) {
				stmt = stmt + ", ?";
			}
			stmt = stmt + " );";
			pstmt = connection.prepareStatement(stmt, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getFirstName());
			pstmt.setString(4, u.getLastName());
			pstmt.setString(5, u.getEmail());

			if( r != null ) {
				pstmt.setInt(6, r.getRoleId());
			}
	        pstmt.executeUpdate();
	        
            rs = pstmt.getGeneratedKeys();
            if(rs.next())
            {
                u.setUserid(rs.getInt(1));
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
	
	public User get(int id) throws Exception {
		User u = null;
		Role r = null;
		
		try {
			pstmt = connection.prepareStatement("SELECT u.username AS username, u.password AS password, u.firstname AS firstname,"
					+ " u.lastname AS lastname, u.email AS email, r.roleid AS roleid, r.rolename AS rolename"
					+ " FROM users AS u LEFT JOIN roles AS r ON u.roleid = r.roleid"
					+ " WHERE u.userid = ?;");
			pstmt.setInt(1, id );
			rs = pstmt.executeQuery();

			if( rs.next() ) {
				r = new Role( rs.getInt("roleid"), rs.getString("rolename") );
				
				u = new User( rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
								rs.getString("lastname"), rs.getString("email") );
				u.setUserid(id);
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
	
	public User get(String username) throws Exception {
		User u = null;
		Role r = null;
		
		try {
			pstmt = connection.prepareStatement("SELECT u.userid AS id, u.username AS username, u.password AS password, u.firstname AS firstname,"
					+ " u.lastname AS lastname, u.email AS email, r.roleid AS roleid, r.rolename AS rolename"
					+ " FROM users AS u LEFT JOIN roles AS r ON u.roleid = r.roleid"
					+ " WHERE u.username = ?;");
			pstmt.setString(1, username );
			rs = pstmt.executeQuery();

			if( rs.next() ) {
				r = new Role( rs.getInt("roleid"), rs.getString("rolename") );
				
				u = new User( rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
								rs.getString("lastname"), rs.getString("email") );
				u.setUserid(rs.getInt("id"));
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
					+ " WHERE userid = ?;");
			
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getFirstName());
			pstmt.setString(4, u.getLastName());
			pstmt.setString(5, u.getEmail());
			pstmt.setInt(6, r.getRoleId());
			pstmt.setInt(7, u.getUserid());
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
	
	public void delete(User u) throws Exception {
		try {
			pstmt = connection.prepareStatement("DELETE FROM users"
					+ " WHERE userid = ?;");
			
			pstmt.setInt(1, u.getUserid());
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
	
	public ArrayList<User> getAll() throws Exception {
		ArrayList<User> userList = new ArrayList<User>();
		User u = null;
		Role r = null;
		
		try {
			pstmt = connection.prepareStatement("SELECT u.userid AS userid, u.username AS username, u.password AS password, u.firstname AS firstname,"
					+ " u.lastname AS lastname, u.email AS email, r.roleid AS roleid, r.rolename AS rolename"
					+ " FROM users AS u INNER JOIN roles AS r ON u.roleid = r.roleid;");
	        rs = pstmt.executeQuery();
	        
			while( rs.next() ) {
				r = new Role( rs.getInt("roleid"), rs.getString("rolename") );
				
				u = new User( rs.getString("username"), rs.getString("password"), rs.getString("firstname"),
								rs.getString("lastname"), rs.getString("email") );
				u.setUserid(Integer.valueOf(rs.getString("userid")));
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
