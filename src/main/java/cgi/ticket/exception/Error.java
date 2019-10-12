package cgi.ticket.exception;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
	private Date timestamp;
	private Integer errorCode;
	private String message;
	
}
