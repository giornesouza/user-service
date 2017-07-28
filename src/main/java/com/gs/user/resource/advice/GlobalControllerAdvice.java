package com.gs.user.resource.advice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.gs.user.exception.EmailAlreadyInUseException;
import com.gs.user.exception.RequiredParamException;
import com.gs.user.exception.UnexpectedParamException;
import com.gs.user.resource.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse internalServerError(Exception ex, WebRequest req) {
	return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
	    RequiredParamException.class, HttpRequestMethodNotSupportedException.class,
	    ConstraintViolationException.class, IllegalArgumentException.class, UnexpectedParamException.class,
	    EmailAlreadyInUseException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse badRequest(Exception ex) {
	return new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFound(Exception ex, WebRequest req) {
	return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = { EntityNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse entityNotFound(Exception ex) {
	return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}
