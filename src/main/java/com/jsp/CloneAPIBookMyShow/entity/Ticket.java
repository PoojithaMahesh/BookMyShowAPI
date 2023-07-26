package com.jsp.CloneAPIBookMyShow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jsp.CloneAPIBookMyShow.enums.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;
	private double totalPrice;
//TicketStatus
	private TicketStatus ticketStatus;

	@ManyToOne
	private MoviewShow moviewShow;

	@OneToMany
	private List<Booking> bookings;

	@ManyToOne
	private Customer customer;

}
