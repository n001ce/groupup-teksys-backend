package com.acormier.groupup.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//custom exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TeamNotFoundException(String message) {
		super(message);
	}
}
