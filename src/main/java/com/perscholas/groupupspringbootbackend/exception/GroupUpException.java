package com.perscholas.groupupspringbootbackend.exception;


public class GroupUpException extends RuntimeException {
    public GroupUpException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public GroupUpException(String exMessage) {
        super(exMessage);
    }
}