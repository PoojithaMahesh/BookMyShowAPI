package com.jsp.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class TicketCannotBeCancelledEXception extends RuntimeException {
private String message;

public TicketCannotBeCancelledEXception(String message) {
	super();
	this.message = message;
}

}
