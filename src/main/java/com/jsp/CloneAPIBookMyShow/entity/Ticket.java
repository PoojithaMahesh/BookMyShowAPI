package com.jsp.CloneAPIBookMyShow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

@ManyToOne
private Show show;

@OneToMany
private List<Booking>  bookings;

@ManyToOne
private Customer  customer;

}
