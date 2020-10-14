package bank.dao.interfaces;

import java.util.List;
import bank.models.User;

public interface UserDAO {

		public User create(User u) throws Exception;
		
		public User get(int id) throws Exception;
		
		public User get(String username) throws Exception;
		
		public void update(User u) throws Exception;
		
		public void delete(User u) throws Exception;
		
		public List<User> getAll() throws Exception;
		
}
