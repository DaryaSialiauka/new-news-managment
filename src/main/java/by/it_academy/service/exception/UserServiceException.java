package by.it_academy.service.exception;

public class UserServiceException extends Exception {

	private static final long serialVersionUID = 4503051267051184573L;

	public UserServiceException() {
		super();
	}

	public UserServiceException(String message) {
		super(message);
	}
	
	public UserServiceException(String message, Exception e) {
		super(message, e);
	}
	
	public UserServiceException(Exception e) {
		super(e);
	}

}
