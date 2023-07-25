package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class ShowIdNOtFoundException extends RuntimeException {

	private String message;

	public ShowIdNOtFoundException(String message) {
		super();
		this.message = message;
	}
	
}
