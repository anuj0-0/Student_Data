package com.kc.exception;

public class IdNotFoundException extends RuntimeException {

	private String message = "ID Not Found";

	public IdNotFoundException(String message) {
		this.message = message;
	}

	public IdNotFoundException() {

	}

	@Override
	public String getMessage() {
		return message;
	}

}
