package com.gs.user.exception;

public class EmailAlreadyInUseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public EmailAlreadyInUseException() {}
    
    public EmailAlreadyInUseException(String message) {
	super(message);
    }

}
