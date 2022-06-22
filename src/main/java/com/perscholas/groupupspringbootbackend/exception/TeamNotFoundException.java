package com.perscholas.groupupspringbootbackend.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//custom exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException {

	public TeamNotFoundException(String message) {
		super(message);
	}
}
