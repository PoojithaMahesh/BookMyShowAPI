package com.jsp.CloneAPIBookMyShow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.CloneAPIBookMyShow.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long>{

}
