package com.jsp.CloneAPIBookMyShow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.entity.Customer;
import com.jsp.CloneAPIBookMyShow.repository.CustemerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustemerRepo repo;

	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}
	public Customer updateCustomer(long customerId,Customer customer) {
		Optional<Customer> optional =repo.findById(customerId);
		if(optional.isPresent()) {
	       customer.setCustomerId(customerId);
			repo.save(customer);
			return customer;
		}
		return null;
	}
	public Customer deleteCustomer(long customerId) {
		Optional<Customer> optional =repo.findById(customerId);
		if(optional.isPresent()) {
			repo.delete(optional.get());
			return optional.get();
		}
		return null;
		
	}
	public Customer getCustomerById(long  customerId) {
		Optional<Customer> optional =repo.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
