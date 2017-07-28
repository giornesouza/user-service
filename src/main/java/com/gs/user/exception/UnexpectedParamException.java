package com.gs.user.exception;

public class UnexpectedParamException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public UnexpectedParamException() {}
    
    public UnexpectedParamException(String message) {
	super(message);
    }

}
