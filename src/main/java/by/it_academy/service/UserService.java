package by.it_academy.service;

import by.it_academy.bean.User;
import by.it_academy.service.exception.UserServiceException;

public interface UserService {
	
	User getUser(int id) throws UserServiceException;
	
	User getUser(String login) throws UserServiceException;

	void saveUser(User user) throws UserServiceException;
	
	void deleteUser(int id) throws UserServiceException;
	
	boolean findLogin(String value) throws UserServiceException;
	
	boolean findPhone(String value) throws UserServiceException;
	
	boolean findEmail(String value) throws UserServiceException;
}
