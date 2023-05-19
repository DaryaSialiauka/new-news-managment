package by.it_academy.service.exception;

public class NewsServiceException extends Exception {

	private static final long serialVersionUID = -9004681146647739486L;
	
	public NewsServiceException(String message, Exception e) {
		super(message, e);		
	}
	
	public NewsServiceException(String message) {
		super(message);		
	}
	
	public NewsServiceException(Exception e) {
		super(e);		
	}

}
