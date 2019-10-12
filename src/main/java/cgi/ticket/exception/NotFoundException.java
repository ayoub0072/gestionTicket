package cgi.ticket.exception;

public class NotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}
