package com.example.democust.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5738764786749234188L;
	
	public ResourceNotFoundException(String message) {
        super(message);
    }
}
