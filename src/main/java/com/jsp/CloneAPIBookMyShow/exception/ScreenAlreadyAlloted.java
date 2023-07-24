package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class ScreenAlreadyAlloted extends RuntimeException {
	private String message;

	public ScreenAlreadyAlloted(String message) {
		super();
		this.message = message;
	}
	

}
