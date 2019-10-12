package cgi.ticket.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Error> notFoundexceptionToDoHandler(Exception ex) {
		Error error = Error.builder()
				.message(ex.getMessage())
				.timestamp(new Date())
				.errorCode(404).build();
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> exceptionToDoHandler(Exception ex) {
		Error error = Error.builder()
				.message(ex.getMessage())
				.timestamp(new Date())
				.errorCode(504).build();
		return new ResponseEntity<Error>(error, HttpStatus.BAD_GATEWAY);
	}
	
}
