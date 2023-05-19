package by.it_academy.dao.exception;

public class NewsDAOException extends Exception{

	private static final long serialVersionUID = -2166606382304164861L;
	
	public NewsDAOException(String message) {
		super(message);
	}

	public NewsDAOException(Exception e) {
		super(e);
	}

	public NewsDAOException(String message, Exception e) {
		super(message, e);
	}


}
