package com.jsp.CloneAPIBookMyShow.dto;

import com.jsp.CloneAPIBookMyShow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDto {
	private long seatId;
	//SeatType
	private SeatType seatType;
}
