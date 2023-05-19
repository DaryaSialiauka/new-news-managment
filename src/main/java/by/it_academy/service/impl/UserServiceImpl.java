package by.it_academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.UserDAOException;
import by.it_academy.service.UserService;
import by.it_academy.service.exception.UserServiceException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO providerUserDAO;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public User getUser(int id) throws UserServiceException {

		try {
			return providerUserDAO.getUser(id);
		} catch (UserDAOException e) {
			throw new UserServiceException("Error get user", e);
		}
	}

	@Transactional
	public User getUser(String login) throws UserServiceException {

		try {
			return providerUserDAO.getUser(login);
		} catch (UserDAOException e) {
			throw new UserServiceException("Error get user", e);
		}
	}

	@Transactional
	public void saveUser(User user) throws UserServiceException {
		try {

			user.setRole(new Role(2));
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);

			providerUserDAO.saveUser(user);
		} catch (UserDAOException e) {
			throw new UserServiceException("Error save user", e);
		}
	}

	@Transactional
	public void deleteUser(int id) throws UserServiceException {
		try {
			providerUserDAO.deleteUser(id);
		} catch (UserDAOException e) {
			throw new UserServiceException("Error delete user", e);
		}
	}

	@Transactional
	public boolean findLogin(String value) throws UserServiceException {
		try {
			return providerUserDAO.findLogin(value);
		} catch (UserDAOException e) {
			throw new UserServiceException("Error find login", e);
		}
	}
	
	@Transactional
	public boolean findPhone(String value) throws UserServiceException {
		try {
			return providerUserDAO.findPhone(value);
		} catch (UserDAOException e) {
			throw new UserServiceException("Error find phone", e);
		}
	}
	
	@Transactional
	public boolean findEmail(String value) throws UserServiceException {
		try {
			return providerUserDAO.findEmail(value);
		} catch (UserDAOException e) {
			throw new UserServiceException("Error find email", e);
		}
	}

}
