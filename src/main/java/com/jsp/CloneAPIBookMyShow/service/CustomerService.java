package com.jsp.CloneAPIBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.dao.CustomerDao;
import com.jsp.CloneAPIBookMyShow.dto.CustomerDto;
import com.jsp.CloneAPIBookMyShow.entity.Customer;
import com.jsp.CloneAPIBookMyShow.exception.CustomerIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(Customer customer) {

		Customer dbCustomer=customerDao.saveCustomer(customer);
		CustomerDto dto=this.modelMapper.map(dbCustomer, CustomerDto.class);
		ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
		structure.setMessage("Customer saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(long customerId,Customer customer2) {
		Customer dbCustomer=customerDao.updateCustomer(customerId, customer2);
		if(dbCustomer!=null) {
			CustomerDto dto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
		}else {
			throw new CustomerIdNotFoundException("sorry failed to update customer");
		}
	}
	public ResponseEntity<ResponseStructure<Customer>> getCustomer(long customerId) {
		
		Customer dbCustomer=customerDao.getCustomerById(customerId);
		if(dbCustomer!=null) {
			ResponseStructure<Customer> structure=new ResponseStructure<Customer>();
			structure.setMessage("Customer Fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbCustomer);
			return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.FOUND);
		}else {
			throw new CustomerIdNotFoundException("sorry failed to get customer");
		}
	}
	
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(long customerId) {
		
		Customer dbCustomer=customerDao.deleteCustomer(customerId);
		if(dbCustomer!=null) {
			ResponseStructure<Customer> structure=new ResponseStructure<Customer>();
			structure.setMessage("Customer deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbCustomer);
			return new ResponseEntity<ResponseStructure<Customer>>(structure,HttpStatus.FOUND);
		}else {
			throw new CustomerIdNotFoundException("sorry failed to Delete customer");
		}
	}
}
