package com.jsp.CloneAPIBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.dao.AddressDao;
import com.jsp.CloneAPIBookMyShow.dto.AddressDto;
import com.jsp.CloneAPIBookMyShow.entity.Address;
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
		
		return null;
	}
	
}
