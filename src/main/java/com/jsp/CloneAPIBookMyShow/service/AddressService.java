package com.jsp.CloneAPIBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.dao.AddressDao;
import com.jsp.CloneAPIBookMyShow.dto.AddressDto;
import com.jsp.CloneAPIBookMyShow.entity.Address;
import com.jsp.CloneAPIBookMyShow.exception.AddressIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class AddressService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(AddressDto addressDto) {
		Address address=this.modelMapper.map(addressDto, Address.class);
		Address dbAddress=addressDao.saveAddress(address);
		ResponseStructure<Address> structure=new ResponseStructure<Address>();
		structure.setMessage("address saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(long addressId, AddressDto addressDto) {
		Address address=this.modelMapper.map(addressDto, Address.class);
		Address dbAddress=addressDao.updateAddress(addressId, address);
		if(dbAddress!=null) {
			ResponseStructure<Address> structure=new ResponseStructure<Address>();
			structure.setMessage("address updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.OK);
		}else {
			throw new AddressIdNotFoundException("Failed to update Address");
		}
	}
	public ResponseEntity<ResponseStructure<Address>> getAddressById(long addressId){
	Address  dbAddress =addressDao.getAddressById(addressId);
	if(dbAddress!=null) {
		ResponseStructure<Address> structure=new ResponseStructure<Address>();
		structure.setMessage("address fetched successfully");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);
	}else {
		throw new AddressIdNotFoundException("Failed to update Address");
	}
	}
	
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(long addressId){
	Address  dbAddress =addressDao.deleteAddress(addressId);
	if(dbAddress!=null) {
		ResponseStructure<Address> structure=new ResponseStructure<Address>();
		structure.setMessage("address fetched successfully");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);
	}else {
		throw new AddressIdNotFoundException("Failed to update Address");
	}
	}
	
}
