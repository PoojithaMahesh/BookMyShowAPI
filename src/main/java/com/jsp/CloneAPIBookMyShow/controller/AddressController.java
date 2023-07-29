package com.jsp.CloneAPIBookMyShow.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CloneAPIBookMyShow.dao.AddressDao;
import com.jsp.CloneAPIBookMyShow.dto.AddressDto;
import com.jsp.CloneAPIBookMyShow.entity.Address;
import com.jsp.CloneAPIBookMyShow.service.AddressService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService service;
	
	
	@ApiOperation(value = "Save Address",notes = "API is used to save the address")
	@ApiResponses(value = {@ApiResponse(code = 201,message = "Successfully created ")})
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@Valid @RequestBody AddressDto addressDto){
		return service.saveAddress(addressDto);
	}
	@ApiOperation(value = "Update Address",notes = "API is used to update the address")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Successfully updated "),
			@ApiResponse(code = 404,message = "Id not found for address")})
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestParam long addressId, @RequestBody AddressDto addressDto){
		return service.updateAddress(addressId,addressDto);
	}
	@ApiOperation(value = "Delete Address",notes = "API is used to delete the address")
	@ApiResponses(value = {@ApiResponse(code = 302,message = "Successfully deleted "),
			@ApiResponse(code = 404,message = "Id not found for address")})
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(@RequestParam long addressId){
		return service.deleteAddressById(addressId);
	}
	@ApiOperation(value = "Select Address",notes = "API is used to select the address")
	@ApiResponses(value = {@ApiResponse(code = 302,message = "Successfully fetched the data "),
			@ApiResponse(code = 404,message = "Id not found for address")})
	@GetMapping
	public ResponseEntity<ResponseStructure<Address>> getAddress(@RequestParam long addressId){
		return service.getAddressById(addressId);
	}
}

