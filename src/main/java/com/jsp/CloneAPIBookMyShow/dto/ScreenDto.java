package com.jsp.CloneAPIBookMyShow.dto;

import com.jsp.CloneAPIBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneAPIBookMyShow.enums.ScreenStatus;
import com.jsp.CloneAPIBookMyShow.enums.ScreenType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenDto {
	private long screenId;
	private String screenName;
//	screentype
	private ScreenType screenType;
//	screenavailability
	private ScreenAvailability screenAvailability;
//	screenStatus
	private ScreenStatus screenStatus;
	private int noOfClassicSeat;
	private int noOfPlatinumSeat;
	private int noOfGoldSeat;
}
