package com.jsp.CloneAPIBookMyShow.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	private long addressId;
	
	private int flatNo;
	@NotNull(message = "Area can't be null")
	@NotBlank(message = "Area cant be blank")
	private String area;
	@NotNull(message = "Landamrak can't be null")
	@NotBlank(message = "alandmark cant be blank")
	private String landmark;
	private String city;
	private String state;
	private String country;
	private long pincode;
}
