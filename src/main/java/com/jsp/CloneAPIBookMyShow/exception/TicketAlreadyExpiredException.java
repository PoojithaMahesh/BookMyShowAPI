package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class TicketAlreadyExpiredException extends RuntimeException {
private String message;

public TicketAlreadyExpiredException(String message) {
	super();
	this.message = message;
}

}
