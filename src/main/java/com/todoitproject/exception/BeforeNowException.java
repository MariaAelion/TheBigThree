package com.todoitproject.exception;
/**
*  @author TheBigThree
* @version 1.0.1
*
*/
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class BeforeNowException extends RuntimeException {
	
	private static final long serialVersionUID = -1749672979746392283L;

		public BeforeNowException() {
			
		}
		

		public BeforeNowException(String message) {
			super(message);
		}
		
	
}
