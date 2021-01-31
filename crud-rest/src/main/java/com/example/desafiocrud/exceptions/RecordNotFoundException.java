package com.example.desafiocrud.exceptions;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 827994593504170475L;

	public RecordNotFoundException(String message) {
		super(message);
	}

}
