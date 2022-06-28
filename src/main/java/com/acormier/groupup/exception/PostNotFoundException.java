package com.acormier.groupup.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//custom exception
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String message) {
		super(message);
	}
}
