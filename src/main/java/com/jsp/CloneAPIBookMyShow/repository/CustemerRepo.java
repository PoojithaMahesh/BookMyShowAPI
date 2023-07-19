package com.jsp.CloneAPIBookMyShow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.CloneAPIBookMyShow.entity.Customer;

public interface CustemerRepo extends JpaRepository<Customer, Long> {

}
