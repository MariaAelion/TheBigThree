package com.todoitproject.exception;
/**
*  @author TheBigThree
* @version 1.0.1
*
*/
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NotIdentifiedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
