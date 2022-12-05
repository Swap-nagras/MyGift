package com.capgemini.eGift.exceptions;
// class for resource not found exception
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
