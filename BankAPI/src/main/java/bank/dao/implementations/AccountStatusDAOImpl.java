package bank.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bank.dao.interfaces.AccountStatusDAO;
import bank.models.AccountStatus;
import bank.services.ConnectionService;

public class AccountStatusDAOImpl implements AccountStatusDAO {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		public AccountStatusDAOImpl() {
			this.connection = ConnectionService.getConnection();
		}

		public AccountStatus get(int id) throws Exception {
			AccountStatus astatus = null;
			
			try {
				pstmt = connection.prepareStatement("SELECT statusid, status"
						+ " FROM accountstatus"
						+ " WHERE statusid = ?;");
				pstmt.setInt(1, id );
		        rs = pstmt.executeQuery();

				if( rs.next() ) {
					astatus = new AccountStatus();
					astatus.setStatusId(rs.getInt("statusid"));
					astatus.setStatus(rs.getString("status"));
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
			
			return astatus;		
		};
		
		public List<AccountStatus> getAll() throws Exception {
			List<AccountStatus> statusList = new ArrayList<AccountStatus>();
			AccountStatus astatus = null;
			
			try {
				pstmt = connection.prepareStatement("SELECT statusid, status FROM accountstatus;");
		        rs = pstmt.executeQuery();
		        
				while( rs.next() ) {
					astatus = new AccountStatus();
					astatus.setStatusId(rs.getInt("statusid"));
					astatus.setStatus(rs.getString("status"));
					statusList.add(astatus);
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
			
			return statusList;				
		};
	}
