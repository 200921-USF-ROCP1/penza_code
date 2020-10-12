package bank.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bank.dao.interfaces.AccountTypeDAO;
import bank.models.AccountType;
import bank.services.ConnectionService;

public class AccountTypeDAOImpl implements AccountTypeDAO {

	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public AccountTypeDAOImpl() {
		this.connection = ConnectionService.getConnection();
	}

	public AccountType get(int id) throws Exception {
		AccountType atype = null;
		
		try {
			pstmt = connection.prepareStatement("SELECT typeid, type"
					+ " FROM accounttype"
					+ " WHERE typeid = ?;");
			pstmt.setInt(1, id );
	        rs = pstmt.executeQuery();

			if( rs.next() ) {
				atype = new AccountType();
				atype.setTypeId(rs.getInt("typeid"));
				atype.setType(rs.getString("type"));
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
		
		return atype;		
	};
	
	public List<AccountType> getAll() throws Exception {
		List<AccountType> typeList = new ArrayList<AccountType>();
		AccountType atype = null;
		
		try {
			pstmt = connection.prepareStatement("SELECT typeid, type FROM accounttype;");
	        rs = pstmt.executeQuery();
	        
			while( rs.next() ) {
				atype = new AccountType();
				atype.setTypeId(rs.getInt("typeid"));
				atype.setType(rs.getString("type"));
				typeList.add(atype);
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
		
		return typeList;				
	};
}