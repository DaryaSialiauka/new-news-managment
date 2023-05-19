package by.it_academy.dao.impl;

import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.it_academy.bean.User;
import by.it_academy.dao.UserDAO;
import by.it_academy.dao.exception.UserDAOException;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public User getUser(int id) throws UserDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			User user = session.get(User.class, id);

			if (Objects.nonNull(user)) {
				return user;
			} else {
				throw new UserDAOException("User didn't find");
			}

		} catch (HibernateException e) {
			throw new UserDAOException("Error get user", e);
		}
	}

	private final static String SELECT_USER_BY_LOGIN = " FROM User WHERE login = :login";
	private final static String LOGIN = "login";

	public User getUser(String login) throws UserDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery(SELECT_USER_BY_LOGIN, User.class);
			query.setParameter(LOGIN, login);
			User user = query.getSingleResult();
			return user;

		} catch (HibernateException e) {
			throw new UserDAOException("Error get user", e);
		}
	}

	public void saveUser(User user) throws UserDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(user);

		} catch (HibernateException e) {
			throw new UserDAOException("Error save user", e);
		}
	}

	public void deleteUser(int id) throws UserDAOException {
		try {

			Session session = sessionFactory.getCurrentSession();
			User user = session.get(User.class, id);
			session.delete(user);

		} catch (HibernateException e) {
			throw new UserDAOException("Error delete user", e);
		}
	}

	public boolean findLogin(String value) throws UserDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery(" FROM User WHERE login = :value", User.class);
			query.setParameter("value", value);
			List<User> user = query.getResultList();

			return user.isEmpty();
		} catch (HibernateException e) {
			throw new UserDAOException("Error find login", e);
		}
	}
	
	public boolean findPhone(String value) throws UserDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery(" FROM User WHERE phone = :value", User.class);
			query.setParameter("value", value);
			List<User> user = query.getResultList();

			return user.isEmpty();
		} catch (HibernateException e) {
			throw new UserDAOException("Error find phone", e);
		}
	}
	
	public boolean findEmail(String value) throws UserDAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery(" FROM User WHERE email = :value", User.class);
			query.setParameter("value", value);
			List<User> user = query.getResultList();

			return user.isEmpty();
		} catch (HibernateException e) {
			throw new UserDAOException("Error find email", e);
		}
	}

}
