package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class SeatIdNotFoundEXception extends RuntimeException {

	private String message;

	public SeatIdNotFoundEXception(String message) {
		super();
		this.message = message;
	}
	
}
