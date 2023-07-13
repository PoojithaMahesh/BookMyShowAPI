package com.jsp.CloneAPIBookMyShow.dto;

import com.jsp.CloneAPIBookMyShow.enums.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
	private long ticketId;
	private double totalPrice;
	//TicketStatus
	private TicketStatus ticketStatus; 
}
