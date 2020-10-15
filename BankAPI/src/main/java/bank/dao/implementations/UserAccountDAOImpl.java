package bank.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bank.dao.interfaces.UserAccountDAO;
import bank.models.UserAccount;
import bank.services.ConnectionService;

public class UserAccountDAOImpl implements UserAccountDAO {

	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public UserAccountDAOImpl() {
		this.connection = ConnectionService.getConnection();
	}
	
	public UserAccount create(UserAccount ua) throws Exception {
		try {
			pstmt = connection.prepareStatement("INSERT INTO useraccounts( userid, accountid ) values"
	        		+ "	( ?, ? );" );
			int i=1;
			pstmt.setInt(i++, ua.getUserId());
			pstmt.setInt(i++, ua.getAccountId());
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
		return ua;	
	}
	
	public ArrayList<Integer> getAccountOwners(int accountid) throws Exception {
		ArrayList<Integer> ualist = new ArrayList<Integer>();
		
		try {
			pstmt = connection.prepareStatement("SELECT userid, accountid"
					+ " FROM useraccounts"
					+ " WHERE accountid = ?;");
			pstmt.setInt(1, accountid );
	        rs = pstmt.executeQuery();

			while( rs.next() ) {
				ualist.add(rs.getInt("userid"));
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
		
		return ualist;			
	}
	
	public ArrayList<Integer> getAccountsForUser(int userid) throws Exception {
		ArrayList<Integer> ualist = new ArrayList<Integer>();
		
		try {
			pstmt = connection.prepareStatement("SELECT userid, accountid"
					+ " FROM useraccounts"
					+ " WHERE userid = ?;");
			pstmt.setInt(1, userid );
	        rs = pstmt.executeQuery();

			while( rs.next() ) {
				ualist.add(rs.getInt("accountid"));
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
		
		return ualist;			
	}
	
	public void delete(UserAccount a) throws Exception {
		
		try {
			pstmt = connection.prepareStatement("DELETE FROM useraccounts"
					+ " WHERE accountid = ? AND userid = ?;");
			
			int i=1;
			pstmt.setInt(i++, a.getAccountId());
			pstmt.setInt(i++, a.getUserId());
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
	}
	
	public ArrayList<UserAccount> getAll() throws Exception {
		ArrayList<UserAccount> ualist = new ArrayList<UserAccount>();
		
		try {
			pstmt = connection.prepareStatement("SELECT userid, accountid"
					+ " FROM useraccounts;");
	        pstmt.executeQuery();

			while( rs.next() ) {
				UserAccount ua = null;
				ua = new UserAccount();
				ua.setUserId(rs.getInt("userid"));
				ua.setAccountId(rs.getInt("accountid"));
				ualist.add(ua);
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
		
		return ualist;		
	}
}
