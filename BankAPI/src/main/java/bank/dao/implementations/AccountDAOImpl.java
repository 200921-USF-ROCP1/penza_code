package bank.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bank.dao.interfaces.AccountDAO;
import bank.models.Account;
import bank.models.AccountStatus;
import bank.models.AccountType;
import bank.services.ConnectionService;

public class AccountDAOImpl implements AccountDAO {

	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public AccountDAOImpl() {
		this.connection = ConnectionService.getConnection();
	}
	
	public Account create(Account a) throws Exception {
		
		try {
			pstmt = connection.prepareStatement("INSERT INTO accounts( accountid, balance, status, type ) values"
	        		+ "	( ?, ?, ?, ? );", PreparedStatement.RETURN_GENERATED_KEYS );
			int i=1;
			pstmt.setInt(i++, a.getAccountId());
			pstmt.setDouble(i++, a.getBalance());
			pstmt.setInt(i++, a.getStatus().getStatusId());
			pstmt.setInt(i++, a.getType().getTypeId());
	        pstmt.executeUpdate();	
	        
            rs = pstmt.getGeneratedKeys();
            if(rs.next())
            {
                a.setAccountId(rs.getInt(1));
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
		return a;
	};
	
	public Account get(int id) throws Exception {
		Account a = null;
		AccountStatus astatus = null;
		AccountType atype = null;
		
		try {
			pstmt = connection.prepareStatement("SELECT a.accountid AS accountid, a.balance AS balance,"
					+ " astatus.statusid AS statusid, astatus.status AS status,"
					+ " atype.typeid AS typeid, atype.type AS type"
					+ " FROM accounts AS a LEFT JOIN accountstatus AS astatus ON a.status = astatus.statusid"
					+ " LEFT JOIN accounttype AS atype ON a.type = atype.typeid"
					+ " WHERE a.accountid = ?;");
			pstmt.setInt(1, id );
	        rs = pstmt.executeQuery();

			if( rs.next() ) {
				a = new Account();
				a.setAccountId(rs.getInt("accountid"));
				a.setBalance(rs.getDouble("balance"));
				
				astatus = new AccountStatus();
				astatus.setStatusId(rs.getInt("statusid"));
				astatus.setStatus(rs.getString("status"));
				a.setStatus(astatus);
				
				atype = new AccountType();
				atype.setTypeId(rs.getInt("typeid"));
				atype.setType(rs.getString("type"));
				a.setType(atype);
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
		
		return a;		
	};
	
	public void update(Account a) throws Exception {
		AccountStatus astatus = null;
		AccountType atype = null;
		String stmt = null;
		
		try {
			astatus = a.getStatus();
			atype = a.getType();
			
			stmt = "UPDATE accounts"
					+ " SET balance=?, type=?"
					+ " WHERE accountid = ?;";
			if( astatus != null ) {
				stmt = stmt + ", status=?";
			}
			if( atype != null ) {
				stmt = stmt + ", type=?";
			}
			pstmt = connection.prepareStatement( stmt );
			
			int i=1;
			pstmt.setDouble(i++, a.getBalance());
			if( astatus != null ) {			
				pstmt.setInt(i++, astatus.getStatusId());
			}
			if( atype != null ) {
				pstmt.setInt(i++, atype.getTypeId());
			}

			pstmt.setInt(i++, a.getAccountId());
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
	
	public void withdraw(Account a, double amount) throws Exception {

		String stmt = null;
		
		try {			
			stmt = "UPDATE accounts"
					+ " SET balance=balance-?"
					+ " WHERE accountid = ?;";
			pstmt = connection.prepareStatement( stmt );
			
			int i=1;
			pstmt.setDouble(i++, amount);
			pstmt.setInt(i++, a.getAccountId());
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
	}
	
	public void deposit(Account a, double amount) throws Exception {

		String stmt = null;
		
		try {			
			stmt = "UPDATE accounts"
					+ " SET balance=balance+?"
					+ " WHERE accountid = ?;";
			pstmt = connection.prepareStatement( stmt );
			
			int i=1;
			pstmt.setDouble(i++, amount);
			pstmt.setInt(i++, a.getAccountId());
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
	}
	
	public void transfer(Account sourceAccount, Account targetAccount, double amount) throws Exception {
		String stmt = null;
		
		try {			
			stmt = "BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;"
					+ " UPDATE accounts"
					+ " SET balance=balance+?"
					+ " WHERE accountid = ?;"
					+ " UPDATE accounts"
					+ " SET balance=balance-?"
					+ " WHERE accountid = ?;"
					+ "COMMIT;";
			pstmt = connection.prepareStatement( stmt );
			
			int i=1;
			pstmt.setDouble(i++, amount);
			pstmt.setInt(i++, targetAccount.getAccountId());
			pstmt.setDouble(i++, amount);
			pstmt.setInt(i++, sourceAccount.getAccountId());
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
	}		
	
	public void delete(Account a) throws Exception {
		try {
			pstmt = connection.prepareStatement("DELETE FROM accounts"
					+ " WHERE accountid = ?;");
			
			pstmt.setInt(1, a.getAccountId());
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
	
	public ArrayList<Account> getAll() throws Exception {
		ArrayList<Account> accountList = new ArrayList<Account>();
		Account a = null;
		AccountStatus astatus = null;
		AccountType atype = null;
		
		try {
			pstmt = connection.prepareStatement("SELECT a.accountid AS accountid, a.balance AS balance,"
					+ " astatus.statusid AS statusid, astatus.status AS status,"
					+ " atype.typeid AS typeid, atype.type AS type"
					+ " FROM accounts AS a LEFT JOIN accountstatus AS astatus ON a.status = astatus.statusid"
					+ " LEFT JOIN accounttype AS atype ON a.type = atype.typeid;");
	        rs = pstmt.executeQuery();
	        
			while( rs.next() ) {
				a = new Account();
				a.setAccountId(rs.getInt("accountid"));
				a.setBalance(rs.getDouble("balance"));
				
				astatus = new AccountStatus();
				astatus.setStatusId(rs.getInt("statusid"));
				astatus.setStatus(rs.getString("status"));
				a.setStatus(astatus);
				
				atype = new AccountType();
				atype.setTypeId(rs.getInt("typeid"));
				atype.setType(rs.getString("type"));
				a.setType(atype);

				accountList.add(a);
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
		
		return accountList;				
	};
}
