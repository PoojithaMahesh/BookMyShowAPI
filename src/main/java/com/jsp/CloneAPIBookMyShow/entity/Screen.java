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
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long screenId;
	private String screenName;
//	screentype
//	screenavailability
//	screenStatus
	@OneToMany
	private List<Seat> seats;
	private int noOfClassicSeat;
	private int noOfPlatinumSeat;
	private int noOfGoldSeat;
	
	@ManyToOne
	private Theatre theatre;

}
