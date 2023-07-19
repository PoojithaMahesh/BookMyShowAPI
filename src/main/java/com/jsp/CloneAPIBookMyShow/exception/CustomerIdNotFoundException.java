package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class CustomerIdNotFoundException extends RuntimeException {

	private String message;

	public CustomerIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
