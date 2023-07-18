package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class TheatreIdNotFoundException extends RuntimeException {
	private String message;

	public TheatreIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	

}
