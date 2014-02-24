package com.sns.domain.exception;

public class TwitterMessageTooLongException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TwitterMessageTooLongException(String message) {
		super(message);
	}

}
