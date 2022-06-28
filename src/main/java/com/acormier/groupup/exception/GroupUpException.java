package com.acormier.groupup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GroupUpException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GroupUpException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public GroupUpException(String exMessage) {
        super(exMessage);
    }
}