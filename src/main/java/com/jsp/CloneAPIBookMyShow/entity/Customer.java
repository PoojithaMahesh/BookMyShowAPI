package com.jsp.CloneAPIBookMyShow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	private String customerName;
	@Min(6000000001l)
	@Max(9999999999l)
	private long customerPhone;
	@Email
	private String customerEmail;
	private String customerPassword;
	@OneToMany(mappedBy = "customer")
	private List<Ticket> tickets;

}
