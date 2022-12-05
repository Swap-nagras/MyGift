package com.capgemini.eGift.exceptions;
// exception class for invalid email exception
public class InvalidEmailException extends RuntimeException {
	public InvalidEmailException(String message) {
		super(message);
	}
}
