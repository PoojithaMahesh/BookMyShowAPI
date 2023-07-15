package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class ProductionHouseIdNotFoundException extends RuntimeException {


	public ProductionHouseIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	private String message;
	

}
