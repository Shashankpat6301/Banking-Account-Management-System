package com.jsp.banking.exception;

public class AccountNotFoundByIdException extends RuntimeException{

	private String message;

	public AccountNotFoundByIdException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
