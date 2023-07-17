package com.jsp.CloneAPIBookMyShow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CloneAPIBookMyShow.dao.AddressDao;
import com.jsp.CloneAPIBookMyShow.dto.AddressDto;
import com.jsp.CloneAPIBookMyShow.entity.Address;
import com.jsp.CloneAPIBookMyShow.service.AddressService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
public class AddressController {

	@Autowired
	private AddressService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody AddressDto addressDto){
		return service.saveAddress(addressDto);
	}
}
