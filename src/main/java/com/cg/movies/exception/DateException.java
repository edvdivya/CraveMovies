package com.cg.movies.exception;

public class DateException extends RuntimeException {

	public DateException() {
		
	}

	public DateException(String message) {
		super(message);
		
	}

	public DateException(Throwable cause) {
		super(cause);
		
	}

	public DateException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public DateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}