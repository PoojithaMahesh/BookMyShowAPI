package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class TicketIdNotFoundException extends RuntimeException {
private String message;

public TicketIdNotFoundException(String message) {
	super();
	this.message = message;
}

}
