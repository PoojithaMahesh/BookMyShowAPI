package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class ShowIsNotActiveException extends RuntimeException {
private String message;

public ShowIsNotActiveException(String message) {
	super();
	this.message = message;
}

}
