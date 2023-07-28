package com.jsp.CloneAPIBookMyShow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CloneAPIBookMyShow.entity.Ticket;
import com.jsp.CloneAPIBookMyShow.service.TicketService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketService service;
	@PostMapping
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(@RequestParam long customerId,
			@RequestParam long showId,
			@RequestParam long seatId){
		return service.saveTicket(customerId,showId,seatId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(@RequestParam long ticketId){
		return service.cancelTicket(ticketId);
	}
}
