package by.it_academy.dao;

import by.it_academy.bean.User;
import by.it_academy.dao.exception.UserDAOException;

public interface UserDAO {
	
	User getUser(int id) throws UserDAOException;
	
	User getUser(String login) throws UserDAOException;
	
	void saveUser(User user) throws UserDAOException;
	
	void deleteUser(int id) throws UserDAOException;
	
	boolean findLogin(String value) throws UserDAOException;
	
	boolean findPhone(String value) throws UserDAOException;
	
	boolean findEmail(String value) throws UserDAOException;

}
