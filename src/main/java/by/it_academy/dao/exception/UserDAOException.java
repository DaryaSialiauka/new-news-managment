package by.it_academy.dao.exception;

public class UserDAOException extends Exception {

	private static final long serialVersionUID = -5684555599787514103L;

	public UserDAOException() {
		super();
	}

	public UserDAOException(String message, Exception e) {
		super(message, e);
	}

	public UserDAOException(String message) {
		super(message);
	}

	public UserDAOException(Exception e) {
		super(e);
	}

	
	
	
}
