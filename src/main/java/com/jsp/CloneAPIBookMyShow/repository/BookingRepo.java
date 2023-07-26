package com.jsp.CloneAPIBookMyShow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.CloneAPIBookMyShow.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
