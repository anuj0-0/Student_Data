package com.kc.exception;

public class NameNotFoundException extends RuntimeException {

	private String message = "Name Not Found";

	public NameNotFoundException(String message) {
		this.message = message;
	}

	public NameNotFoundException() {

	}

	@Override
	public String getMessage() {
		return message;
	}

}
